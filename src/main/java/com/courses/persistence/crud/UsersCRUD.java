package com.courses.persistence.crud;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courses.web.config.User;


public interface UsersCRUD extends JpaRepository<User,String> {
    
    Optional<User> findByUsername(String username);
}
