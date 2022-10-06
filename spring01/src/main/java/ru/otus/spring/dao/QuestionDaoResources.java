package ru.otus.spring.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuestionDaoResources implements QuestionDao {

    private final String filename;

    public QuestionDaoResources(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public ArrayList<String> readQuestions() throws IOException {

        var strings = new ArrayList<String>();

        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(getFilename())) {
            var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            while(line != null) {
                strings.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return strings;
    }
}
