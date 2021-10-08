package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.entity.User;
import com.akgarg.employeemanagerbackend.model.SignupRequest;
import com.akgarg.employeemanagerbackend.model.SignupResponse;
import com.akgarg.employeemanagerbackend.service.contract.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SignupServiceImpl implements SignupService {

    private final DatabaseServiceImpl databaseService;

    @Autowired
    public SignupServiceImpl(DatabaseServiceImpl databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public SignupResponse signup(SignupRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(Arrays.toString(request.getPassword()));
        user.setPhone(request.getPhone());

        return this.databaseService.addUser(user);
    }
}
