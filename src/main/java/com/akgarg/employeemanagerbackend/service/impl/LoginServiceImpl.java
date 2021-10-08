package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.entity.User;
import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.LoginResponse;
import com.akgarg.employeemanagerbackend.service.contract.LoginService;
import com.akgarg.employeemanagerbackend.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LoginServiceImpl implements LoginService {

    private final DatabaseServiceImpl databaseService;

    @Autowired
    public LoginServiceImpl(DatabaseServiceImpl databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public LoginResponse doLogin(LoginRequest request) {
        if (!this.databaseService.isEmailRegistered(request.getEmail())) {
            return new LoginResponse("Email not registered", ConstantUtils.USER_UNREGISTERED, null, null, null);
        }

        User user = this.databaseService.getUserByEmail(request.getEmail());
        return user.getPassword().equals(Arrays.toString(request.getPassword())) ?
                new LoginResponse("Login successful", ConstantUtils.AUTHENTICATION_SUCCESS,
                        user.getEmail(), "ygr72tf54yg754yg", user.getId()) :
                new LoginResponse("Incorrect password", ConstantUtils.AUTHENTICATION_FAILED,
                        null, null, null);
    }
}
