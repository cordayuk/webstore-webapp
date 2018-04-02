package com.fakeshop.webapp.dao;

import com.fakeshop.webapp.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Long> {
}
