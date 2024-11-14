package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SpaceData;
import com.example.demo.model.Space;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.Default.UserRepo;
import com.example.demo.services.SpaceService;
import com.example.demo.services.UserService;

@RestController
public class SpaceController {
    
    @Autowired
    SpaceService service;

    @Autowired
    SpaceRepository repo;


    @Autowired
    UserService user;

    @PostMapping("/spaces")
    public ResponseEntity<String> create(@RequestBody SpaceData data){
        if(repo.findByName(data.name),  user.)
    }

    @GetMapping("/spaces{?}")
}
