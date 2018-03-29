package com.fakeshop.webapp.service;

import com.fakeshop.webapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Principal;


public interface UserService extends UserDetailsService{
    User findByEmail(String email);
    void save(User user);
    void delete(User user);
    User getCurrentUser(Principal principal);
}
