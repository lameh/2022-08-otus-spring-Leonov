package ru.otus.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.QuestionList;
import ru.otus.spring.services.QuestionService;
import ru.otus.spring.services.QuestionServiceImpl;

import java.io.IOException;
import java.util.Iterator;

public class App {

    final String[] args;

    public App(String[] args) {
        this.args = args;
    }

    private void execute() throws IOException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = applicationContext.getBean(QuestionServiceImpl.class);

        Iterator<QuestionList> questionListIterator = questionService.iterateQuestions();
        while(questionListIterator.hasNext()) {
            var questionList = questionListIterator.next();
            System.out.println(questionList.getId() + ". " + questionList.getQuestion());
            System.out.println("Answwers: " + questionList.getAnswers());
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        App app = new App(args);
        app.execute();
    }
}
