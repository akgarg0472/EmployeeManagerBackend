package com.akgarg.employeemanagerbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeRequest {

    private String userId;

    private String employeeId;

    private String firstName;
    private String lastName;
    private String email;
    private String department;
}
