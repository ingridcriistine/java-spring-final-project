package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QuestionData;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.services.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @Autowired
    SpaceRepository spaceRepo;

    @PostMapping
    public ResponseEntity<String> createSpace(@RequestBody QuestionData data){

        var space = spaceRepo.findById(data.idSpace());

        if(!space.isPresent())
            return new ResponseEntity<>("Espaço não encontrado", HttpStatus.BAD_REQUEST);

        questionService.createQuestion(data.text(), space.get());
        
        return new ResponseEntity<>("Espaço criado com sucesso", HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable Long id){
        
        if(questionService.DeleteQuestion(id))
            return new ResponseEntity<>("Espaço deletado!", HttpStatus.ACCEPTED);
            
        return new ResponseEntity<>("Não foi possível deletar esse Espaço!", HttpStatus.BAD_REQUEST);
    }

}
