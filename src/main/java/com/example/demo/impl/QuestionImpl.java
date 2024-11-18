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
    public Question getQuestion(Long id) {
        try {
            var question = questionRepo.getReferenceById(id);

            return question;

        } catch (jakarta.persistence.EntityNotFoundException e) {
            return null;
        }
    }
    
    @Override
    public List<Question> getQuestions(Long space, Integer page, Integer size) {
        try {
            var questions = questionRepo.findQuestionsWithPagination(space, (page-1)*size, size);

            return questions;

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
        
        try {
            questionRepo.deleteById(id);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return false;
        }
        return true;
    }
    
}
