package ru.otus.spring.dao;

import ru.otus.spring.domain.QuestionEntity;

import java.util.ArrayList;
import java.util.List;

public interface QuestionEntityDao {

    ArrayList<String> readQuestions();
}
