package com.example.webapp.service;

import com.example.webapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.security.Principal;



public interface UserService extends UserDetailsService{
    User findByEmail(String email);
    void saveNewCustomer(User user);
    void save(User user);
    void delete(User user);
    User getCurrentUser(Principal principal);
    User findById(Long id);
    Iterable<User> getAllUsers();
}
