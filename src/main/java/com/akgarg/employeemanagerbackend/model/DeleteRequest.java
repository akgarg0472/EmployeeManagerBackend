package com.akgarg.employeemanagerbackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteRequest {

    private String auth_Id;
    private String emp_id;
}
