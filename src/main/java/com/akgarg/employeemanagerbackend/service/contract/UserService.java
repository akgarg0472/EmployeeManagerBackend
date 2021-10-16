package com.akgarg.employeemanagerbackend.service.contract;

import com.akgarg.employeemanagerbackend.entity.Employee;
import com.akgarg.employeemanagerbackend.model.EmployeeRequest;
import com.akgarg.employeemanagerbackend.model.EmployeesResponse;

import java.util.List;

@SuppressWarnings("unused")
public interface UserService {

    Employee saveEmployee(Employee employee);

    Employee saveEmployee(String userId, String firstName, String lastName, String email,
                          String address, String phone, String department);

    Employee getEmployee(String employeeEmail);

    Employee getEmployeeUsingEmployeeId(String employeeId);

    List<EmployeesResponse> getAllEmployeesUsingUserId(String userId);

    List<Employee> getAllEmployeesUsingDepartment(String employeeDepartment);

    boolean updateEmployee(Employee employee);

    boolean updateEmployee(String userId, String employeeId, String firstName, String lastName,
                           String email, String address, String phone, String department);

    boolean updateEmployee(EmployeeRequest employeeRequest);

    boolean deleteEmployee(Employee employee);

    boolean deleteEmployee(String employeeId, String userId);

    boolean deleteAllEmployees(String userId);

}
