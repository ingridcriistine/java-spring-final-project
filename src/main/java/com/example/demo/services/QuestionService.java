package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.QuestionData;
import com.example.demo.model.Question;
import com.example.demo.model.Space;

public interface QuestionService {
    List<QuestionData> getQuestions(Long space,Integer page,Integer size);
    QuestionData getQuestion(Long id);
    Question createQuestion(String question,Space space);
    boolean DeleteQuestion(Long id);
}
