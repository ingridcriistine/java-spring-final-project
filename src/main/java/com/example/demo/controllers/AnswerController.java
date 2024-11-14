package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.model.Answer;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.UserRepository;

@RestController
public class AnswerController {
    @Autowired
    PermissionRepository PermissionRepo;

    @PostMapping("/answer")
    public ResponseEntity<String> createAswer(@RequestAttribute("token") Token token, @RequestBody Answer data){
        
        if(PermissionRepo.findById(token.getId()).get() == null){

        }
    }
}
