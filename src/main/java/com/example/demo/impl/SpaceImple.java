package com.example.demo.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Permission;
import com.example.demo.model.Space;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.services.SpaceService;

public class SpaceImple implements SpaceService {

    @Autowired
    SpaceRepository repo;

    @Autowired 
    PermissionRepository permission;

    @Override
    public Space createSpace(String name, User user) {
        var checkSpace = repo.findByName(name);

        if(!checkSpace.isEmpty()){
            return null;
        }

        var space = new Space();
        space.setName(name);
        repo.save(space);
        
        
        var UserAdmin = new Permission();
        UserAdmin.setIsAdmin(true);
        UserAdmin.setSpace(space);
        UserAdmin.setUser(user);

        permission.save(UserAdmin);
        return space;
    }

    @Override
    public List<Space> getSpaces(String name, Integer page, Integer size) {
        List<Space> Spaces = repo.findSpacesWithPagination(name,(page-1) * size,size);
        
        if (Spaces.isEmpty()) {
            return Collections.emptyList();
        }
        
        return Spaces;
    }

    @Override
    public boolean isOwner(Long idSpace, User user) {
        var listPermission = user.getPermissions();

        var permission = listPermission.stream()
        .filter(u -> u.getSpace().getId()
        .equals(idSpace))
        .collect(Collectors.toList());

        if(permission.isEmpty()){
            return false;
        }

        return permission.get(0).getIsAdmin();  
    }


    @Override
    public boolean deleteSpace(Long id) {
        var checkId = repo.findById(id);

        if(checkId.isEmpty()){
            return false;
        }
        // var permissionId = permission.findBySpaceId(id).get(0).getId();
        try {
            // permission.deleteById(permissionId);
            repo.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }


    @Override
    public Space getSpace(Long id) {
        var space = repo.findById(id);

        if(!space.isEmpty()){
            return null;
        }

        return space.get();
    }  
}
