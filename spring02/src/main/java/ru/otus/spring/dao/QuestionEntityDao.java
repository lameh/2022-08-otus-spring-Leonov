package ru.otus.spring.dao;

import ru.otus.spring.domain.QuestionEntity;

import java.util.List;

public interface QuestionEntityDao {

    List<QuestionEntity> getQuestions();
}
