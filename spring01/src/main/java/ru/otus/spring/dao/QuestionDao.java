package ru.otus.spring.dao;

import java.io.IOException;
import java.util.ArrayList;

public interface QuestionDao {

    ArrayList<String > readQuestions() throws IOException;
}
