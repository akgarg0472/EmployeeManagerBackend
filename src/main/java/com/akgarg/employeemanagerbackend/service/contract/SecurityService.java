package com.akgarg.employeemanagerbackend.service.contract;

import com.akgarg.employeemanagerbackend.model.CsrfTokenResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityService {

    public boolean authenticate(HttpServletRequest request, String username, char[] password);

    public CsrfTokenResponse generateCSRF(HttpServletRequest request, HttpServletResponse response, boolean store);

    public CsrfTokenResponse getCSRFToken(HttpServletRequest request, HttpServletResponse response);
}
