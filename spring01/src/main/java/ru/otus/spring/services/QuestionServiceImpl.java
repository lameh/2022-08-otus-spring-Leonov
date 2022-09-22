package ru.otus.spring.services;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.QuestionList;

import java.io.IOException;
import java.util.Iterator;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public Iterator<QuestionList> iterateQuestions() throws IOException {
        return questionDao.iterateQuestions();
    }
}
