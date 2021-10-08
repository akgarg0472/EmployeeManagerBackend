package com.akgarg.employeemanagerbackend.repository;

import com.akgarg.employeemanagerbackend.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findEmployeesByUserId(String userId);

    List<Employee> findEmployeesByDepartment(String department);

    Employee findEmployeeByEmail(String email);

    int deleteByIdAndUserId(String id, String userId);

    int deleteByUserId(String userId);
}
