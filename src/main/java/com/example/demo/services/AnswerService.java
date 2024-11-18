package com.example.demo.services;

import com.example.demo.model.Answer;

public interface AnswerService {
    Answer createAnswer(Long idQuestion, String answer);
}