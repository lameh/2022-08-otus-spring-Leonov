package ru.otus.spring.services;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.QuestionList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public Iterator<QuestionList> processQuestions(ArrayList<String> strings) throws IOException {
        ArrayList<QuestionList> questions = new ArrayList<>();
        Iterator<String> iterator = strings.iterator();

        while (iterator.hasNext()) {
            var tokens = new StringTokenizer(iterator.next(), ",");
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
        }
        return questions.iterator();
    }

    @Override
    public void outputQuestions(Iterator<QuestionList> questions) {
        while(questions.hasNext()) {
            var questionList = questions.next();
            System.out.println(questionList.getId() + ". " + questionList.getQuestion());
            System.out.println("Answers: " + questionList.getAnswers());
            System.out.println();
        }
    }
}
