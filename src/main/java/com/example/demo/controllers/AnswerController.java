package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AnswerData;
import com.example.demo.dto.Token;
import com.example.demo.model.Answer;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.services.AnswerService;

@RestController
public class AnswerController {

    @Autowired
    AnswerService service;

    @Autowired
    PermissionRepository PermissionRepo;


    @PostMapping("/answer")
    public ResponseEntity<String> answer(@RequestAttribute("token") Token token,@RequestBody AnswerData data){
        
        
        if(PermissionRepo.findById(token.getId()).isEmpty()){
            return new ResponseEntity<>("Você não possui permissao para comentar", HttpStatus.OK);
        }
        
        var newAnswer = service.createAnswer(data.idQuestion(), data.answer());

        if(newAnswer == null){
            return new ResponseEntity<>("Pergunta inválida", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
