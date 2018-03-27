package com.fakeshop.webapp.dao;

import com.fakeshop.webapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
