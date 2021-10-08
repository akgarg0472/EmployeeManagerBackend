package com.akgarg.employeemanagerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EmployeeManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagerBackendApplication.class, args);
    }

}
