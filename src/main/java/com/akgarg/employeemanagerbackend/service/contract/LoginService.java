package com.akgarg.employeemanagerbackend.service.contract;

import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.LoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    LoginResponse doLogin(HttpServletRequest req, HttpServletResponse res, LoginRequest request);
}
