package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionEntity;
import ru.otus.spring.exception.QuestionEntityDaoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionEntityDaoImpl implements QuestionEntityDao {

    @Value("${questions.filename}")
    String fileName;

    @Override
    public List<QuestionEntity> getQuestions() {
        List<QuestionEntity> questions = new ArrayList<>();
        try (InputStream file = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (file != null) {
                try (InputStreamReader inputStreamReader = new InputStreamReader(file);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    while (bufferedReader.ready()) {
                        String[] strings = bufferedReader.readLine().split(";");
                        int num;
                        try {
                            num = Integer.parseInt(strings[0]);
                        } catch (NumberFormatException e) {
                            throw new QuestionEntityDaoException("Number of question must be integer!", e);
                        }
                        var text = strings[1];
                        var question = new Question(num, text);
                        int correctAnswerNum;
                        try {
                            correctAnswerNum = Integer.parseInt(strings[2]);
                        } catch (NumberFormatException e) {
                            throw new QuestionEntityDaoException("Number of correct question must be integer!", e);
                        }
                        ArrayList<Answer> answ = new ArrayList<>();
                        for (int i = 3; i < strings.length; i++) {
                            answ.add(new Answer(strings[i]));
                        }
                        questions.add(new QuestionEntity(question, answ, correctAnswerNum));
                    }
                }
            }
        } catch (IOException e) {
            throw new QuestionEntityDaoException("Can't open file!", e);
        }
        return questions;
    }
}
