package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.Answer;

public record QuestionData(
    String text,
    Long idSpace,
    Set<Answer> answers
) {}
