package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.entity.Employee;
import com.akgarg.employeemanagerbackend.service.contract.UserService;
import com.akgarg.employeemanagerbackend.utils.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final DatabaseServiceImpl databaseService;

    @Autowired
    public UserServiceImpl(DatabaseServiceImpl databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employee == null) {
            throw new ApplicationException("Employee can't be null");
        }

        return this.databaseService.addEmployee(employee);
    }

    @Override
    public Employee saveEmployee(String userId, String firstName, String lastName, String email,
                                 String address, String phone, String department) {
        Employee employee = new Employee(userId, firstName, lastName, email, address, phone, department);
        return this.saveEmployee(employee);
    }

    @Override
    public Employee getEmployee(String employeeEmail) {
        return this.databaseService.getEmployeeByEmail(employeeEmail);
    }

    @Override
    public Employee getEmployeeUsingEmployeeId(String employeeId) {
        return this.databaseService.getEmployeeByEmployeeId(employeeId);
    }

    @Override
    public List<Employee> getAllEmployeesUsingUserId(String userId) {
        return this.databaseService.getEmployeesByUserId(userId);
    }

    @Override
    public List<Employee> getAllEmployeesUsingDepartment(String employeeDepartment) {
        return this.databaseService.getEmployeesByDepartment(employeeDepartment);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        this.databaseService.updateEmployee(employee);
        return true;
    }

    @Override
    public boolean updateEmployee(String userId, String employeeId, String firstName, String lastName,
                                  String email, String address, String phone, String department) {
        this.databaseService.updateEmployee(userId, employeeId, firstName, lastName, email, address, phone, department);
        return true;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return this.databaseService.deleteEmployee(employee) != 0;
    }

    @Override
    public boolean deleteEmployee(String employeeId, String userId) {
        return this.databaseService.deleteEmployee(employeeId, userId) != 0;
    }

    @Override
    public boolean deleteAllEmployees(String userId) {
        return this.databaseService.deleteEmployeesByUserId(userId) != 0;
    }
}
