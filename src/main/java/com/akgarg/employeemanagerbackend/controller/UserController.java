package com.akgarg.employeemanagerbackend.controller;

import com.akgarg.employeemanagerbackend.entity.Employee;
import com.akgarg.employeemanagerbackend.model.EmployeeResponse;
import com.akgarg.employeemanagerbackend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public EmployeeResponse getAllEmployees(HttpServletRequest request) {
        // todo later
        List<Employee> employeeList = this.userService.getAllEmployeesUsingUserId(request.getHeader("userId"));
        return new EmployeeResponse(employeeList, "Request success", 200);
    }


    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
    public EmployeeResponse getEmployee(@PathVariable String employeeId) {
        // todo later
        Employee employee = this.userService.getEmployeeUsingEmployeeId(employeeId);
        return new EmployeeResponse(employee, "Request success", 200);
    }


    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public EmployeeResponse addEmployee() {
        // todo later
        Employee employee = new Employee();
        Employee emp = this.userService.saveEmployee(employee);
        return new EmployeeResponse(emp, "Request success", 200);
    }
}
