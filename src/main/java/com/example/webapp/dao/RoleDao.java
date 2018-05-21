package com.example.webapp.dao;

import com.example.webapp.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Long> {
}
