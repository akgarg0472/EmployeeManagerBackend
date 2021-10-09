package com.akgarg.employeemanagerbackend.controller;

import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.JwtLoginResponse;
import com.akgarg.employeemanagerbackend.service.impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final JwtServiceImpl jwtService;

    @Autowired
    public LoginController(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JwtLoginResponse login(@RequestBody LoginRequest request) {
        return this.jwtService.authenticateUserAndGenerateJwtToken(request);
    }

}
