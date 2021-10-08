package com.akgarg.employeemanagerbackend.security;

import com.akgarg.employeemanagerbackend.entity.User;
import com.akgarg.employeemanagerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);

        if (user != null) {
            return new UserDetailsImpl(user);
        }

        throw new UsernameNotFoundException("Invalid credentials, user not found");
    }
}
