package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.model.JwtLoginResponse;
import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.security.UserDetailsImpl;
import com.akgarg.employeemanagerbackend.security.UserDetailsServiceImpl;
import com.akgarg.employeemanagerbackend.service.contract.JwtService;
import com.akgarg.employeemanagerbackend.utils.ConstantUtils;
import com.akgarg.employeemanagerbackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public JwtServiceImpl(JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager,
                          UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public JwtLoginResponse authenticateUserAndGenerateJwtToken(LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), Arrays.toString(request.getPassword()));
            this.authenticationManager.authenticate(authenticationToken);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            String errorMessage = e.getMessage().equals("Bad credentials") ? "Invalid email or password" : e.getMessage();
            return new JwtLoginResponse(errorMessage, ConstantUtils.AUTHENTICATION_FAILED, null, null, null);
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) this.userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtUtil.generateToken(userDetails);

        return new JwtLoginResponse("Login success", ConstantUtils.AUTHENTICATION_SUCCESS, request.getEmail(), token,
                userDetails.getUserId());
    }
}
