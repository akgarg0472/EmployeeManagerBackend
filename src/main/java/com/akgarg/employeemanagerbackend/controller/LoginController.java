package com.akgarg.employeemanagerbackend.controller;

import com.akgarg.employeemanagerbackend.model.CsrfTokenResponse;
import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.LoginResponse;
import com.akgarg.employeemanagerbackend.service.impl.LoginServiceImpl;
import com.akgarg.employeemanagerbackend.service.impl.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final LoginServiceImpl loginService;
    private final SecurityServiceImpl securityService;


    @Autowired
    public LoginController(LoginServiceImpl loginService,
                           SecurityServiceImpl securityService) {
        this.loginService = loginService;
        this.securityService = securityService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest req) {
        System.out.println(req.getHeader("X-CSRF-TOKEN"));
        return this.loginService.doLogin(request);
    }


    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public CsrfTokenResponse getCsrfToken(HttpServletRequest request, HttpServletResponse response) {
        return this.securityService.generateCSRF(request, response, true);
    }

}
