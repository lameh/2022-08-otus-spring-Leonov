package ru.otus.spring.dao;

import ru.otus.spring.domain.QuestionList;

import java.io.IOException;
import java.util.Iterator;

public interface QuestionDao {

    Iterator<QuestionList> iterateQuestions() throws IOException;
}
