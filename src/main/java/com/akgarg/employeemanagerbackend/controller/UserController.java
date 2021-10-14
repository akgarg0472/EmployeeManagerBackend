package com.akgarg.employeemanagerbackend.controller;

import com.akgarg.employeemanagerbackend.entity.Employee;
import com.akgarg.employeemanagerbackend.model.DeleteRequest;
import com.akgarg.employeemanagerbackend.model.EmployeeRequest;
import com.akgarg.employeemanagerbackend.model.EmployeeResponse;
import com.akgarg.employeemanagerbackend.model.EmployeesResponse;
import com.akgarg.employeemanagerbackend.service.impl.UserServiceImpl;
import com.akgarg.employeemanagerbackend.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    public EmployeeResponse getAllEmployees(HttpServletRequest request, Principal principal) {
        if (principal == null) {
            return new EmployeeResponse(null, "Session expired, Please login again", ConstantUtils.AUTHENTICATION_FAILED);
        }

        List<EmployeesResponse> employeeList = this.userService.getAllEmployeesUsingUserId(request.getHeader("userId"));
        return new EmployeeResponse(employeeList, "Request success", 200);
    }


    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
    public EmployeeResponse getEmployee(@PathVariable String employeeId, Principal principal) {
        if (principal == null) {
            return new EmployeeResponse
                    (null, "Please login again to continue", ConstantUtils.AUTHENTICATION_FAILED);
        }

        Employee employee = this.userService.getEmployeeUsingEmployeeId(employeeId);
        return new EmployeeResponse(employee, "Request success", 200);
    }


    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest, Principal principal) {
        if (principal == null) {
            return new EmployeeResponse
                    (null, "Please login again to continue", ConstantUtils.AUTHENTICATION_FAILED);
        }

        // todo later
        Employee saveEmployee = new Employee(
                employeeRequest.getUserId(),
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail(),
                employeeRequest.getAddress(),
                employeeRequest.getPhone(),
                employeeRequest.getDepartment()
        );
        Employee employee = this.userService.saveEmployee(saveEmployee);
        return new EmployeeResponse(employee, "Request success", 200);
    }


    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public EmployeeResponse editEmployee(@RequestBody @Nullable EmployeeRequest employeeRequest, Principal principal) {
        if (principal == null) {
            return new EmployeeResponse
                    (null, "Please login again to continue", ConstantUtils.AUTHENTICATION_FAILED);
        }

        return new EmployeeResponse();
    }


    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    public EmployeeResponse deleteEmployee(@RequestBody DeleteRequest deleteRequest, Principal principal) {
        if (principal == null) {
            return new EmployeeResponse(null, "Please login again to continue", ConstantUtils.AUTHENTICATION_FAILED);
        }

        boolean deleteEmployee = this.userService.deleteEmployee(deleteRequest.getEmp_id(), deleteRequest.getAuth_id());
        return deleteEmployee ?
                new EmployeeResponse("", "Employee deleted successfully", 200)
                :
                new EmployeeResponse(null, "Delete employee failed", 202);
    }
}