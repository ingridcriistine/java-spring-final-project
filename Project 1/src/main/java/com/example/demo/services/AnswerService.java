package com.example.demo.services;

import com.example.demo.model.Answer;
import com.example.demo.model.Question;

public interface AnswerService {
    Answer createAnswer(Question question,String answer);
}