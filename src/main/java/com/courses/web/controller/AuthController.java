package com.courses.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courses.domain.dtos.LoginDTO;
import com.courses.web.config.JWTUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    

public AuthController(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
    this.jwtUtil = jwtUtil;
    this.authenticationManager = authenticationManager;
}



@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDTO request) {

    Authentication authenticated = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password())
    );
    if (authenticated.isAuthenticated()) {
        String token = jwtUtil.generateToken(request.username());
        return ResponseEntity.ok(token);
    }else{
        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
    

}
