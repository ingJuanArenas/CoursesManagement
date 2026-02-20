package com.courses.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.courses.persistence.repository.UserRepositoryImpl;
import com.courses.web.config.User;


@Service
public class UserService {
    

    private final UserRepositoryImpl userRepository;
    private final PasswordEncoder passwordEncoder;
    
    


    public UserService(UserRepositoryImpl userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(String username){
        return userRepository.getUserById(username); 
    }

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveUser(user);
    }

    public void deleteUser(String  id){
        userRepository.deleteUser(id);
    }
}

