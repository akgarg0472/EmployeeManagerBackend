package com.akgarg.employeemanagerbackend.service.impl;

import com.akgarg.employeemanagerbackend.entity.Employee;
import com.akgarg.employeemanagerbackend.entity.User;
import com.akgarg.employeemanagerbackend.model.SignupResponse;
import com.akgarg.employeemanagerbackend.repository.EmployeeRepository;
import com.akgarg.employeemanagerbackend.repository.UserRepository;
import com.akgarg.employeemanagerbackend.service.contract.DatabaseService;
import com.akgarg.employeemanagerbackend.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DatabaseServiceImpl implements DatabaseService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DatabaseServiceImpl(UserRepository userRepository,
                               EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isEmailRegistered(String email) {
        User user = this.userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public boolean isUserRegistered(String email) {
        return false;
    }

    @Override
    public boolean isUserRegistered(User user) {
        return this.isEmailRegistered(user.getEmail());
    }

    @Override
    public SignupResponse addUser(User user) {
        if (this.isEmailRegistered(user.getEmail())) {
            return new SignupResponse("Email already registered", ConstantUtils.USER_ALREADY_REGISTERED);
        }

        this.userRepository.save(user);
        return new SignupResponse("Registration successful", ConstantUtils.USER_REGISTERED_SUCCESSFULLY);
    }

    @Override
    public SignupResponse addUser(String firstName, String lastName, String email, String password, String phone) {
        User user = new User(firstName, lastName, email, password, phone);
        return this.addUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(String userId) {
        Optional<User> user = this.userRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return user != null ? this.userRepository.save(user) : null;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        User user = getUserByEmail(email);

        int deleteByEmail = this.userRepository.deleteByEmail(email);
        if (deleteByEmail == 1) {
            this.deleteEmployeesByUserId(user.getId());
        }

        return deleteByEmail == 1;
    }

    @Override
    public boolean deleteUserById(String id) {
        return this.userRepository.deleteUserById(id) == 1;
    }

    @Override
    public boolean deleteUser(User user) {
        return this.deleteUserById(user.getId());
    }

    /*
     *
     *
     * EMPLOYEES COLLECTION
     *
     *
     * */

    @Override
    public Employee addEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee addEmployee(String userId, String firstName, String lastName, String email,
                                String address, String phone, String department) {
        Employee employee = new Employee(userId, firstName, lastName, email, address, phone, department);
        return this.addEmployee(employee);
    }

    @Override
    public Employee getEmployeeByEmployeeId(String employeeId) {
        Optional<Employee> employee = this.employeeRepository.findById(employeeId);
        return employee.orElse(null);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return this.employeeRepository.findEmployeeByEmail(email);
    }

    @Override
    public List<Employee> getEmployeesByUserId(String userId) {
        return this.employeeRepository.findEmployeesByUserId(userId);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        return this.employeeRepository.findEmployeesByDepartment(department);
    }

    @Override
    public void updateEmployee(Employee employee) {
        this.addEmployee(employee);
    }

    @Override
    public void updateEmployee(String userId, String employeeId, String firstName, String lastName,
                               String email, String address, String phone, String department) {
        Employee employee = new Employee();
        this.updateEmployee(employee);
    }

    @Override
    public int deleteEmployee(Employee employee) {
        return this.deleteEmployee(employee.getId(), employee.getUserId());
    }

    @Override
    public int deleteEmployee(String employeeId, String userId) {
        return this.employeeRepository.deleteByIdAndUserId(employeeId, userId);
    }

    @Override
    public int deleteEmployeesByUserId(String userId) {
        return this.employeeRepository.deleteByUserId(userId);
    }
}
