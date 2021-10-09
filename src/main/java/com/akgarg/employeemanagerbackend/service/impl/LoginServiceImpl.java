package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.JwtLoginResponse;
import com.akgarg.employeemanagerbackend.service.contract.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    private final SecurityServiceImpl securityService;

    @Autowired
    public LoginServiceImpl(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }


    @Override
    public JwtLoginResponse doLogin(HttpServletRequest req, HttpServletResponse res, LoginRequest request) {
        return this.securityService.authenticate(req, res, request);
    }
}
