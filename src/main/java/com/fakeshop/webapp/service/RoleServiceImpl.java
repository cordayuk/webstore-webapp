package com.fakeshop.webapp.service;

import com.fakeshop.webapp.dao.RoleDao;
import com.fakeshop.webapp.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id).get();
    }
}
