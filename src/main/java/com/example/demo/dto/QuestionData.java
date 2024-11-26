package com.example.demo.dto;

import java.util.Set;

public record QuestionData(
    String text,
    Long questionId,
    Long idSpace,
    Set<AnswerText> answers
) {}
