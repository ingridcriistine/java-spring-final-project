package com.example.demo.dto;

import java.util.Set;

public record QuestionDataAnwers(
    String text,
    Long idSpace,
    Set<AnswerText> answers
) {}
