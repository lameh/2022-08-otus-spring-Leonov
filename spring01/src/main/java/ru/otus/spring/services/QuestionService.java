package ru.otus.spring.services;

import ru.otus.spring.domain.QuestionList;

import java.io.IOException;
import java.util.Iterator;

public interface QuestionService {

    Iterator<QuestionList> iterateQuestions() throws IOException;
}
