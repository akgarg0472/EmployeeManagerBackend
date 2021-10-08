package com.akgarg.employeemanagerbackend.controller;

import com.akgarg.employeemanagerbackend.model.SignupRequest;
import com.akgarg.employeemanagerbackend.model.SignupResponse;
import com.akgarg.employeemanagerbackend.service.impl.SignupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class SignupController {

    private final SignupServiceImpl signupService;

    @Autowired
    public SignupController(SignupServiceImpl signupService) {
        this.signupService = signupService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public SignupResponse signup(@RequestBody SignupRequest request) {
        return this.signupService.signup(request);
    }
}
