package ru.otus.spring.service;

import ru.otus.spring.domain.QuestionEntity;

import java.util.List;

public interface QuestionEntityService {

    List<QuestionEntity> getQuestion();
}
