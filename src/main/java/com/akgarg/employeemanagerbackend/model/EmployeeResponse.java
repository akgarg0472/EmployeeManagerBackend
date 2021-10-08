package com.akgarg.employeemanagerbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeResponse {

    Object payload;
    private String message;
    private int status;
}
