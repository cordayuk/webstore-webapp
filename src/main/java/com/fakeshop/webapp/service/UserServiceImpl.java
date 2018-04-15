package com.fakeshop.webapp.service;

import com.fakeshop.webapp.dao.RoleDao;
import com.fakeshop.webapp.dao.UserDao;
import com.fakeshop.webapp.entity.Order;
import com.fakeshop.webapp.entity.Role;
import com.fakeshop.webapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

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
        user.setEnabled(true);
        user.setNonExpired(true);
        user.setNonLocked(true);
        Role customerRole = roleDao.findById(1L).get();
        user.setRole(customerRole);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        save(user);
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
    public User findById(Long id) {

        return userDao.findById(id).get();
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userDao.findAll();
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
