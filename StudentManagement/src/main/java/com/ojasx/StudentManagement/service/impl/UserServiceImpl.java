package com.ojasx.StudentManagement.service.impl;

import com.ojasx.StudentManagement.model.Users;
import com.ojasx.StudentManagement.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users =  userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Invalid username or password"));

        return User.withUsername(username)
                .password(users.getPassword())
                .disabled(!users.isActive())
                .build();
    }
}
