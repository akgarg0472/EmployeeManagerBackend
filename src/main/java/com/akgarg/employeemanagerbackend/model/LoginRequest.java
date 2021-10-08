package com.akgarg.employeemanagerbackend.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    private String email;
    private char[] password;
}
