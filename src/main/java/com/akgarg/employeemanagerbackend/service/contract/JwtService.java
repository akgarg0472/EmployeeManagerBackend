package com.akgarg.employeemanagerbackend.service.contract;

import com.akgarg.employeemanagerbackend.model.JwtLoginResponse;
import com.akgarg.employeemanagerbackend.model.LoginRequest;

public interface JwtService {

    JwtLoginResponse authenticateUserAndGenerateJwtToken(LoginRequest request);
}
