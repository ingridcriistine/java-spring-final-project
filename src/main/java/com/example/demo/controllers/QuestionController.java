package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.example.demo.dto.AnswerText;
import com.example.demo.dto.QuestionData;
import com.example.demo.dto.QuestionDataAnwers;
import com.example.demo.dto.Token;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.repositories.AnswerRepository;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.services.QuestionService;

@RestController
@RequestMapping
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerRepository answerRepo;

    @Autowired
    SpaceRepository spaceRepo;

    @Autowired
    PermissionRepository PermissionRepo;

    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionDataAnwers> getQuestionById(@PathVariable Long id) {

        Question question = questionService.getQuestion(id);

        if (question == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        Set<AnswerText> answers = new HashSet<>();
        
        for (Answer answer : question.getAnswers()){
            answers.add(new AnswerText(answer.getAnswer()));
        }

        QuestionDataAnwers data = new QuestionDataAnwers(
                question.getQuestion(),
                question.getSpace().getId(),
                answers);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/questions/{space}")
    public ResponseEntity<List<QuestionData>> getQuestions(
            @PathVariable Long space,
            @RequestParam(name = "page", required = true) Integer page,
            @RequestParam(name = "size", required = true) Integer size) {

        var questions = questionService.getQuestions(space, (page - 1) * size, size);

        
        List<QuestionData> data = new ArrayList<>();
        
        for (Question question : questions) {
            data.add(new QuestionData(
                question.getQuestion(),
                question.getSpace().getId()
            ));
        }

        if (questions.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/question")
    public ResponseEntity<String> createQuestion(@RequestAttribute("token") Token token,@RequestBody QuestionData data) {


        if (PermissionRepo.havePermission(token.getId(), data.idSpace()) == 0) {
            return new ResponseEntity<>("Você nao tem permissão", HttpStatus.BAD_REQUEST);
        }

        var space = spaceRepo.findById(data.idSpace());

        if (!space.isPresent())
            return new ResponseEntity<>("Espaço não encontrado", HttpStatus.NOT_FOUND);

        questionService.createQuestion(data.text(), space.get());

        return new ResponseEntity<>("Pergunta criada com sucesso", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity<String> deleteQuestion(@RequestAttribute("token") Token token, @PathVariable Long id) {

        if (PermissionRepo.findById(token.getId()).get().getIsAdmin() == false) {
            return new ResponseEntity<>("Voce nao pode", HttpStatus.FORBIDDEN);
        }

        if (questionService.DeleteQuestion(id))
            return new ResponseEntity<>("Pergunta deletada!", HttpStatus.ACCEPTED);

        return new ResponseEntity<>("Não foi possível deletar essa Pergunta!", HttpStatus.NOT_FOUND);
    }

}
