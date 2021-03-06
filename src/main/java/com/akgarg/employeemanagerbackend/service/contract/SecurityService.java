package com.akgarg.employeemanagerbackend.service.contract;

import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.JwtLoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityService {

    JwtLoginResponse authenticate(HttpServletRequest req, HttpServletResponse res, LoginRequest request);

    String getCSRFToken(HttpServletRequest request, HttpServletResponse response);

}
