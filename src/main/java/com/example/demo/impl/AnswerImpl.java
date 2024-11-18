package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Answer;
import com.example.demo.repositories.AnswerRepository;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.services.AnswerService;

public class AnswerImpl implements AnswerService {

    @Autowired 
    PermissionRepository permission;

    @Autowired 
    QuestionRepository repoQuest;

    @Autowired 
    AnswerRepository repo;

    @Override
    public Answer createAnswer(Long idQuestion, String answer) {
        var checkQuestion = repoQuest.findById(idQuestion);

        if(checkQuestion.isEmpty()){
            return null;
        }

        var newAnswer = new Answer();
        newAnswer.setQuestion(checkQuestion.get());
        newAnswer.setAnswer(answer);
        repo.save(newAnswer);

        return newAnswer;
    }   
    
}
