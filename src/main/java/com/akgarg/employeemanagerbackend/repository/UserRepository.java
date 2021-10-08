package com.akgarg.employeemanagerbackend.repository;

import com.akgarg.employeemanagerbackend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    int deleteByEmail(String email);

    int deleteUserById(String id);
}
