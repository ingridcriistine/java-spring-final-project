package com.example.demo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.QuestionData;
import com.example.demo.model.Question;
import com.example.demo.model.Space;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.services.QuestionService;

public class QuestionImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepo;

    @Override
    public QuestionData getQuestion(Long id) {
        try {
            var question = questionRepo.getReferenceById(id);

            QuestionData data = new QuestionData(question.getQuestion(), question.getSpace().getId());

            return data;

        } catch (jakarta.persistence.EntityNotFoundException e) {
            return null;
        }
    }
    
    @Override
    public List<QuestionData> getQuestions(Long space, Integer page, Integer size) {
        try {
            var questions = questionRepo.findQuestionsWithPagination(space, page, size);

            List<QuestionData> data = new ArrayList<>();

            for (Question question : questions) {
                data.add(new QuestionData(question.getQuestion(), question.getSpace().getId()));
            }

            return data;

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
