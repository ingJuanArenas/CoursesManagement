package com.courses.domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.courses.persistence.crud.UsersCRUD;
import com.courses.web.config.Role;
import com.courses.web.config.User;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UsersCRUD usersCRUD;

    

    public UserSecurityService(UsersCRUD usersCRUD) {
        this.usersCRUD = usersCRUD;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFound = usersCRUD.findById(username).orElseThrow(()-> new UsernameNotFoundException("Username: " + username + " not found"));
        String[] roles = userFound.getRoles().stream().map(Role::getName).toArray(String[]::new);
        return org.springframework.security.core.userdetails.User.builder().username(userFound.getUsername())   
                                            .password(userFound.getPassword())
                                            .roles(roles)
                                            .build();
                
                            
    }
    
}
