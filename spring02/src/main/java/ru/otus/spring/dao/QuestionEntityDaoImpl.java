package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.spring.exception.MyTestingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Repository
public class QuestionEntityDaoImpl implements QuestionEntityDao {

    @Value("${questions.filename}")
    java.lang.String fileName;

    @Override
    public ArrayList<String> readQuestions() {
        var strings = new ArrayList<java.lang.String>();
        try (InputStream file = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (file != null) {
                try (InputStreamReader inputStreamReader = new InputStreamReader(file);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        strings.add(line);
                        line = bufferedReader.readLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new MyTestingException("Can't open file!", e);
        }
        return strings;
    }
}
