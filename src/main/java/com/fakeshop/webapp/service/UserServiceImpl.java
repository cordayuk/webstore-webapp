package com.fakeshop.webapp.service;

import com.fakeshop.webapp.dao.UserDao;
import com.fakeshop.webapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserDao userDao;


    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
