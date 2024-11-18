package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Permission;
import com.example.demo.model.User;
import com.example.demo.model.Space;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.services.PermissionService;

public class PermissionImplements implements PermissionService {

    @Autowired
    PermissionRepository repo;

    @Override
    public Permission createPermission(User user, Space space, boolean IsAdmin) {
        
        Permission newPermission = new Permission();
        newPermission.setUser(user);
        newPermission.setSpace(space);
        newPermission.setIsAdmin(IsAdmin);
        repo.save(newPermission);
        return newPermission;
    }

    @Override
    public boolean IsAdm(User user, Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'IsAdm'");
    }
    
}
