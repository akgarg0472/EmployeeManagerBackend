package com.akgarg.employeemanagerbackend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("employee")
public class Employee {

    @Id
    private String id;

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String department;

    public Employee(String userId,
                    String firstName,
                    String lastName,
                    String email,
                    String address,
                    String phone,
                    String department) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.department = department;
    }

    public boolean compareEmployee(Employee employee) {
        return this.id.equals(employee.getId()) &&
                this.userId.equals(employee.getUserId()) &&
                this.firstName.equals(employee.getFirstName()) &&
                this.lastName.equals(employee.getLastName()) &&
                this.email.equals(employee.getEmail()) &&
                this.address.equals(employee.getAddress()) &&
                this.phone.equals(employee.getPhone()) &&
                this.department.equals(employee.getDepartment());
    }
}
