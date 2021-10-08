package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.entity.User;
import com.akgarg.employeemanagerbackend.model.SignupRequest;
import com.akgarg.employeemanagerbackend.model.SignupResponse;
import com.akgarg.employeemanagerbackend.service.contract.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SignupServiceImpl implements SignupService {

    private final DatabaseServiceImpl databaseService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SignupServiceImpl(DatabaseServiceImpl databaseService,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.databaseService = databaseService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public SignupResponse signup(SignupRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(this.bCryptPasswordEncoder.encode(Arrays.toString(request.getPassword())));
        user.setPhone(request.getPhone());

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRole("ROLE_USER");

        return this.databaseService.addUser(user);
    }
}
