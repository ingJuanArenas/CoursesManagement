package com.courses.domain.repository;

import com.courses.web.config.User;

public interface UserRepository {
    User getUserById(String username);
    User saveUser(User user);
    void deleteUser(String id);
}
