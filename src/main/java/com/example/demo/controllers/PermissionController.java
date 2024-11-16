package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.LoginData;
import com.example.demo.dto.PermissionData;
import com.example.demo.dto.SecurityToken;
import com.example.demo.dto.Token;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;

public class PermissionController {
    
    
    @Autowired
    PermissionRepository PermissionRepo;
    
    
    
    @PostMapping("/permission")
    public ResponseEntity<String> addUserInSpace(@RequestAttribute("token") Token token,@RequestBody PermissionData data) {

        if (PermissionRepo.findByUserId(token.getId()).get(0).getIsAdmin()== false) {
            return new ResponseEntity<>("Voce nao pode", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Voce nao pode", HttpStatus.OK);

    }
}
