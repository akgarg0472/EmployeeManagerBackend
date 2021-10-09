package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.model.LoginRequest;
import com.akgarg.employeemanagerbackend.model.JwtLoginResponse;
import com.akgarg.employeemanagerbackend.service.contract.SecurityService;
import com.akgarg.employeemanagerbackend.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public JwtLoginResponse authenticate(HttpServletRequest req, HttpServletResponse res, LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), Arrays.toString(request.getPassword()));

            // Authenticates the user
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticate);
            SecurityContextHolder.setContext(securityContext);

            // Add the security context to session
            HttpSession session = req.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            if (securityContext.getAuthentication().isAuthenticated()) {
                System.out.println(securityContext.getAuthentication().getAuthorities());
                String csrfToken = this.getCSRFToken(req, res);
//                session.setAttribute("auth_token", csrfToken);
//                session.setAttribute("auth_id", securityContext.getAuthentication().getCredentials());

                return new JwtLoginResponse("Login successful", ConstantUtils.AUTHENTICATION_SUCCESS, request.getEmail(),
                        csrfToken, "2318");
            }

            // todo fix (although very rare to reach this point)
            return null;
        } catch (AuthenticationException e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return new JwtLoginResponse(e.getMessage(), ConstantUtils.AUTHENTICATION_FAILED,
                    null, null, null);
        }
    }


    @Override
    public String getCSRFToken(HttpServletRequest request, HttpServletResponse response) {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken loadToken = csrfTokenRepository.loadToken(request);
        CsrfToken csrfToken = loadToken != null ? loadToken : csrfTokenRepository.generateToken(request);

        return csrfToken.getToken();
    }


}
