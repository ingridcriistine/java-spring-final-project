package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Space;
import com.example.demo.services.SpaceService;

@RestController
public class SpaceController {
    
    @Autowired
    SpaceService service;


    @PostMapping("/spaces")
    public ResponseEntity<String> create(@RequestBody Space data){

       
    }
}