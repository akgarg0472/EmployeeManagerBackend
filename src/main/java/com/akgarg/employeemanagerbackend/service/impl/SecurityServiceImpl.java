package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.LoginResponse;
import com.akgarg.employeemanagerbackend.service.contract.SecurityService;
import com.akgarg.employeemanagerbackend.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public LoginResponse authenticate(HttpServletRequest req, HttpServletResponse res, LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), Arrays.toString(request.getPassword()));

        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authenticate);
            HttpSession session = req.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

            if (sc.getAuthentication().isAuthenticated()) {
                String csrfToken = this.getCSRFToken(req, res);
                session.setAttribute("auth_token", csrfToken);
                session.setAttribute("auth_id", sc.getAuthentication().getCredentials());

                return new LoginResponse("Login successful", ConstantUtils.AUTHENTICATION_SUCCESS, request.getEmail(),
                        csrfToken, "2318");
            }

            // todo fix (although very rare to reach this point)
            return null;
        } catch (AuthenticationException e) {
            return new LoginResponse(e.getMessage(), ConstantUtils.AUTHENTICATION_FAILED,
                    null, null, null);
        }
    }


    @Override
    public String getCSRFToken(HttpServletRequest request, HttpServletResponse response) {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken loadToken = csrfTokenRepository.loadToken(request);
        CsrfToken csrfToken = loadToken != null ? loadToken : csrfTokenRepository.generateToken(request);
        csrfTokenRepository.saveToken(csrfToken, request, response);

        return csrfToken.getToken();
    }


}
