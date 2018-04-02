package com.fakeshop.webapp.service;

import com.fakeshop.webapp.dao.RoleDao;
import com.fakeshop.webapp.dao.UserDao;
import com.fakeshop.webapp.entity.Role;
import com.fakeshop.webapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;


    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void saveNewCustomer(User user) {
        User newCustomer = user;
        newCustomer.setEnabled(true);
        newCustomer.setNonExpired(true);
        newCustomer.setNonLocked(true);
        Role customerRole = roleDao.findById(1L).get();
        newCustomer.setRole(customerRole);
        newCustomer.setPassword(BCrypt.hashpw(newCustomer.getPassword(), BCrypt.gensalt()));
        save(newCustomer);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User getCurrentUser(Principal principal) {
        return findByEmail(principal.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

}
