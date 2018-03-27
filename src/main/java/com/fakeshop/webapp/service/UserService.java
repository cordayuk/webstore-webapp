package com.fakeshop.webapp.service;

import com.fakeshop.webapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService{
    User findByEmail(String email);
}
