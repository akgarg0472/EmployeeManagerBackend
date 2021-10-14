package com.akgarg.employeemanagerbackend.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeesResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
}
