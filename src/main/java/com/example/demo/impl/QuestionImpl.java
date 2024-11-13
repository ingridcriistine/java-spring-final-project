package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Question;
import com.example.demo.model.Space;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.services.QuestionService;

public class QuestionImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepo;

    @Override
    public List<Question> getQuestions(Space space, Integer page, Integer size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getQuestions'");
    }

    @Override
    public Question getQuestion(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getQuestion'");
    }

    @Override
    public Question createQuestion(String question, Space space) {
        
        Question newQuestion = new Question();

        newQuestion.setQuestion(question);

        newQuestion.setSpace(space);

        questionRepo.save(newQuestion);

        return newQuestion;
    }

    @Override
    public boolean DeleteQuestion(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeleteQuestion'");
    }
    
}
