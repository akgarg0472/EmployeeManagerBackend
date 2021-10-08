package com.akgarg.employeemanagerbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupRequest {

    private String firstName;
    private String lastName;
    private String email;
    private char[] password;
    private char[] confirmPassword;
    private String phone;
}
