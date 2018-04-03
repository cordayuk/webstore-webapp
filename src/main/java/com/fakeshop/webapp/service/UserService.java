package com.fakeshop.webapp.service;

import com.fakeshop.webapp.entity.Order;
import com.fakeshop.webapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.Collection;


public interface UserService extends UserDetailsService{
    User findByEmail(String email);
    void saveNewCustomer(User user);
    void save(User user);
    void delete(User user);
    User getCurrentUser(Principal principal);
}
