package com.akgarg.employeemanagerbackend.service.contract;

import com.akgarg.employeemanagerbackend.model.SignupRequest;
import com.akgarg.employeemanagerbackend.model.SignupResponse;

public interface SignupService {

    SignupResponse signup(SignupRequest request);
}
