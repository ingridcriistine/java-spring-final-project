package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QuestionData;
import com.example.demo.dto.Token;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.services.QuestionService;

@RestController
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @Autowired
    SpaceRepository spaceRepo;

    @Autowired
    PermissionRepository PermissionRepo;

    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionData> getQuestionById(@PathVariable Long id) {
        
        var question = questionService.getQuestion(id);

        if(question == null)
            return new ResponseEntity<>(question, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @SuppressWarnings("null")
    @GetMapping("/questions/{space}")
    public ResponseEntity<List<QuestionData>> getQuestions(
        @PathVariable Long space,
        @RequestParam(name= "page", required=true) Integer page,
        @RequestParam(name= "size", required=true) Integer size
        ) {
            
        var questions = questionService.getQuestions(space, page, size);
        

        if(questions.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    
    @PostMapping("/question")
    public ResponseEntity<String> createQuestion(@RequestBody QuestionData data){

        var space = spaceRepo.findById(data.idSpace());

        if(!space.isPresent())
            return new ResponseEntity<>("Espaço não encontrado", HttpStatus.NOT_FOUND);

        questionService.createQuestion(data.text(), space.get());
        
        return new ResponseEntity<>("Pergunta criada com sucesso", HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/question/{id}")
    public ResponseEntity<String> deleteQuestion(@RequestAttribute("token") Token token,@PathVariable Long id){

        System.out.println(PermissionRepo.findByUserId(token.getId()).get(0));
        if (PermissionRepo.findById(token.getId()).get().getIsAdmin() == false) {
            return new ResponseEntity<>("Voce nao pode", HttpStatus.FORBIDDEN);
        }
        if(questionService.DeleteQuestion(id))
            return new ResponseEntity<>("Pergunta deletada!", HttpStatus.ACCEPTED);
            
        return new ResponseEntity<>("Não foi possível deletar essa Pergunta!", HttpStatus.NOT_FOUND);
    }

}
