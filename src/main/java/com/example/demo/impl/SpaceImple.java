package com.example.demo.impl;

import java.security.Permission;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

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
        space.setPermissions(user.getPermissions());
    
        return space;
    }

    @Override
    public List<Space> getSpaces(String name, Integer page, Integer size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpaces'");
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

        if(!checkId.isEmpty()){
            return false;
        }

        try {
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
