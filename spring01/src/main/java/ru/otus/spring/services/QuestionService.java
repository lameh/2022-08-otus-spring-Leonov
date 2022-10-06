package ru.otus.spring.services;

import ru.otus.spring.domain.QuestionList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public interface QuestionService {

    Iterator<QuestionList> processQuestions(ArrayList<String> strings) throws IOException;

    void outputQuestions(Iterator<QuestionList> questions);
}
