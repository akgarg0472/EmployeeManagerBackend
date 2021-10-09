package com.akgarg.employeemanagerbackend.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtLoginResponse {

    private String message;
    private int status;
    private String auth_user;
    private String auth_token;
    private String auth_userId;
}
