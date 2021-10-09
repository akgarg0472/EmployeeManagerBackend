package com.akgarg.employeemanagerbackend.controller;

import com.akgarg.employeemanagerbackend.model.GeneralResponse;
import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.LoginResponse;
import com.akgarg.employeemanagerbackend.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final LoginServiceImpl loginService;

    @Autowired
    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest req, HttpServletResponse res) {
        return this.loginService.doLogin(req, res, request);
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public GeneralResponse info(Principal principal, HttpServletRequest request) {
        return new GeneralResponse("generated principal is: ", principal);
    }

}
