package com.courses.persistence.repository;

import org.springframework.stereotype.Repository;

import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.repository.UserRepository;
import com.courses.persistence.crud.UsersCRUD;
import com.courses.web.config.User;

@Repository
public class UserRepositoryImpl implements UserRepository{
    
    private final UsersCRUD usersCRUD;

    public UserRepositoryImpl(UsersCRUD usersCRUD) {
        this.usersCRUD = usersCRUD;
    }

    @Override
    public User getUserById(String username) {
       return usersCRUD.findById(username).orElseThrow(()-> new NotFoundException("User with provided id not found"));
    }


    @Override
    public User saveUser(User user) {
        return usersCRUD.save(user);
    }


    @Override
    public void deleteUser(String id) {
        User userFound = usersCRUD.findById(id).orElseThrow(()-> new NotFoundException("Username not found"));
        usersCRUD.delete(userFound);
    }
    }

   

