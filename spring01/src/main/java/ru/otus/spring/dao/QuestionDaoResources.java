package ru.otus.spring.dao;

import ru.otus.spring.domain.QuestionList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class QuestionDaoResources implements QuestionDao {

    private String filename;

    public QuestionDaoResources(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public Iterator<QuestionList> iterateQuestions() throws IOException {

        ArrayList<QuestionList> questions = new ArrayList<>();

        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("question_list.csv")) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            var line = bufferedReader.readLine();

            while (line != null) {
                var tokens = new StringTokenizer(line, ",");
                int id;
                try {
                    id = Integer.parseInt(tokens.nextToken());
                } catch (NumberFormatException ex) {
                    throw new IOException(ex);
                }
                var question = tokens.nextToken();
                var answers = "";
                while (tokens.hasMoreTokens()) {
                    answers += tokens.nextToken() + ", ";
                }
                questions.add(new QuestionList(id, question, answers));
                line = bufferedReader.readLine();
            }
        }
        return questions.iterator();
    }
}
