package com.akgarg.employeemanagerbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteRequest {

    private String auth_id;
    private String emp_id;
}
