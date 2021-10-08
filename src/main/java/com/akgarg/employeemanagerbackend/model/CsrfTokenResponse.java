package com.akgarg.employeemanagerbackend.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CsrfTokenResponse {

    private String message;
    private String token;
    private int status;
}
