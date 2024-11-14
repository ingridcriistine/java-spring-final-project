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
        try {
            var question = questionRepo.getReferenceById(id);
            return question;
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return null;
        }
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
        questionRepo.deleteById(id);
        
        try {
            questionRepo.getReferenceById(id);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return true;
        }
        return false;
    }
    
}