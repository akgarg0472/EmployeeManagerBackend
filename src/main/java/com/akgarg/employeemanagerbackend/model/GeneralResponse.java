package com.akgarg.employeemanagerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeneralResponse {

    private String message;
    private Object payload;
}
