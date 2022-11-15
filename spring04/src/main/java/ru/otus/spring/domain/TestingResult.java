package ru.otus.spring.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class TestingResult {

    @NonNull private final int totalAnswers;
    @NonNull private final int correctAnswers;
}
