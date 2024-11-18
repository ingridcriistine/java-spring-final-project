package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.PermissionData;
import com.example.demo.dto.Token;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PermissionService;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.model.Space;

@RestController
public class PermissionController {
    
    
    @Autowired
    PermissionRepository PermissionRepo;
    
    @Autowired
    PermissionService permissionService;

    @Autowired
    SpaceRepository spaceRepo;

    @Autowired
    UserRepository userRepo;
    
    @PostMapping("/permission")
    public ResponseEntity<String> addUserInSpace(@RequestAttribute("token") Token token, @RequestBody PermissionData data) {

        var permission = PermissionRepo.findByUserId(token.getId());

        if(permission.isEmpty() || permission.get(0).getIsAdmin() == false){
            return new ResponseEntity<>("Voce não possui permissão para a ação", HttpStatus.OK);
        }

        User user = userRepo.findById(data.userId()).get();
        Space space = spaceRepo.findById(data.spaceId()).get();
        permissionService.createPermission(user, space, data.isAdmin());

        return new ResponseEntity<>("Permission created with sucess", HttpStatus.OK);

    }
}
