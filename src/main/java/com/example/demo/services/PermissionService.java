package com.example.demo.services;

import com.example.demo.model.Space;
import com.example.demo.model.User;
import com.example.demo.model.Permission;

public interface PermissionService {
    Permission createPermission(User user, Space space, boolean IsAdmin);
    boolean IsAdm(User user,Long id);
}
