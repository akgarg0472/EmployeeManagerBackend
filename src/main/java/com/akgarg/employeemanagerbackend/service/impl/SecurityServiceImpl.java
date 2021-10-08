package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.model.CsrfTokenResponse;
import com.akgarg.employeemanagerbackend.service.contract.SecurityService;
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
    public boolean authenticate(HttpServletRequest request, String username, char[] password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, Arrays.toString(password));

        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authenticate);
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
            return sc.getAuthentication().isAuthenticated();
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    @Override
    public CsrfTokenResponse generateCSRF(HttpServletRequest request, HttpServletResponse response, boolean store) {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken loadToken = csrfTokenRepository.loadToken(request);
        CsrfToken csrfToken = loadToken != null ? loadToken : csrfTokenRepository.generateToken(request);
        csrfTokenRepository.saveToken(csrfToken, request, response);

        return new CsrfTokenResponse("CSRF token generated successfully", csrfToken.getToken(), 200);
    }


    @Override
    public CsrfTokenResponse getCSRFToken(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken csrfToken = new HttpSessionCsrfTokenRepository().loadToken(request);
        return new CsrfTokenResponse("Generated token", csrfToken.getToken(), 200);
    }

}
