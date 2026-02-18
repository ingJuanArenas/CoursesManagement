package com.courses.domain.service;

import org.springframework.stereotype.Service;

import com.courses.persistence.repository.UserRepositoryImpl;
import com.courses.web.config.User;


@Service
public class UserService {
    

    private final UserRepositoryImpl userRepository;

    public UserService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    

    public User getUserById(String username){
        return userRepository.getUserById(username); 
    }

    public User saveUser(User user){
        return userRepository.saveUser(user);
    }

    public void deleteUser(String  id){
        userRepository.deleteUser(id);
    }
}

