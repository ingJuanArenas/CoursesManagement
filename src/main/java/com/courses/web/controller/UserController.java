package com.courses.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courses.domain.service.UserService;
import com.courses.web.config.User;

@RestController
@RequestMapping("/api/users")
@EnableMethodSecurity(securedEnabled = true)
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    @Secured("ROLE_ADMIN") // SOON : ONLY USERS CAN SEE THEIR PROFILES AVOIDING BROCKEN ACCESS CONTROL
    public ResponseEntity<User> getUserById(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserById(username));
    }


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN") // SOON : ONLY USERS CAN DELETE THEIR PROFILES AVOIDING BROCKEN ACCESS CONTROL
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
