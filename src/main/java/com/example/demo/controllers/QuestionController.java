package com.example.demo.controllers;

import java.util.List;

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
import com.example.demo.model.Question;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.services.QuestionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.net.httpserver.HttpsConfigurator;

@RestController
@RequestMapping("/question")
public class QuestionController {
    
    @Autowired
    QuestionService questionService;

    @Autowired
    SpaceRepository spaceRepo;

    // @GetMapping("/{space}")
    // public ResponseEntity<List<Question>> getMethodName(
    //     @PathVariable Long space,
    //     @RequestParam Integer page,
    //     @RequestParam Integer size
    //     ) {
            
    //     var questions = questionService.getQuestions(space, page, size);
        
    //     if(questions.isEmpty())
    //         return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    //     return new ResponseEntity<>(questions, HttpStatus.OK);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        
        var question = questionService.getQuestion(id);

        if(question == null)
            return new ResponseEntity<>(question, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(question, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionData data){

        var space = spaceRepo.findById(data.idSpace());

        if(!space.isPresent())
            return new ResponseEntity<>("Espaço não encontrado", HttpStatus.BAD_REQUEST);

        questionService.createQuestion(data.text(), space.get());
        
        return new ResponseEntity<>("Pergunta criada com sucesso", HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
        
        if(questionService.DeleteQuestion(id))
            return new ResponseEntity<>("Pergunta deletada!", HttpStatus.ACCEPTED);
            
        return new ResponseEntity<>("Não foi possível deletar essa Pergunta!", HttpStatus.BAD_REQUEST);
    }

}
