package com.akgarg.employeemanagerbackend.service.contract;

import com.akgarg.employeemanagerbackend.entity.Employee;
import com.akgarg.employeemanagerbackend.entity.User;
import com.akgarg.employeemanagerbackend.model.SignupResponse;

import java.util.List;

@SuppressWarnings("unused")
public interface DatabaseService {

    boolean isEmailRegistered(String email);

    boolean isUserRegistered(String email);

    boolean isUserRegistered(User user);

    SignupResponse addUser(User user);

    SignupResponse addUser(String firstName, String lastName, String email, String password, String phone);

    User getUserByEmail(String email);

    User getUserById(String userId);

    User updateUser(User user);

    boolean deleteUserByEmail(String email);

    boolean deleteUserById(String id);

    boolean deleteUser(User user);


    //
    //
    //  EMPLOYEE METHODS
    //
    //

    Employee addEmployee(Employee employee);

    Employee addEmployee(String userId, String firstName, String lastName,
                     String email, String address, String phone, String department);

    Employee getEmployeeByEmployeeId(String employeeId);

    Employee getEmployeeByEmail(String email);

    List<Employee> getEmployeesByUserId(String userId);

    List<Employee> getEmployeesByDepartment(String department);

    void updateEmployee(Employee employee);

    void updateEmployee(String userId, String employeeId, String firstName, String lastName,
                        String email, String address, String phone, String department);

    int deleteEmployee(Employee employee);

    int deleteEmployee(String employeeId, String userId);

    int deleteEmployeesByUserId(String userId);
}
