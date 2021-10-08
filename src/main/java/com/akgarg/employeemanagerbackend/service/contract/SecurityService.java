package com.akgarg.employeemanagerbackend.service.contract;

import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.LoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityService {

    public LoginResponse authenticate(HttpServletRequest req, HttpServletResponse res, LoginRequest request);

    public String getCSRFToken(HttpServletRequest request, HttpServletResponse response);

}
