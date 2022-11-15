package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.exception.MyTestingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionEntityDaoImpl implements QuestionEntityDao {

    private final String fileName;

    public QuestionEntityDaoImpl(@Value("${app.csv-file-path.question}") String filename) {
        this.fileName = filename;
    }

    @Override
    public List<String> readQuestions() {
        var strings = new ArrayList<String>();
        try (InputStream file = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (file != null) {
                try (InputStreamReader inputStreamReader = new InputStreamReader(file, StandardCharsets.UTF_8);
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
