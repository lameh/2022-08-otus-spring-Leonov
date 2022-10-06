package ru.otus.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoResources;
import ru.otus.spring.services.QuestionService;
import ru.otus.spring.services.QuestionServiceImpl;

import java.io.IOException;

public class App {

    final String[] args;

    public App(String[] args) {
        this.args = args;
    }

    private void execute() throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = applicationContext.getBean(QuestionServiceImpl.class);
        QuestionDao questionDao = applicationContext.getBean(QuestionDaoResources.class);
        questionService.outputQuestions(questionService.processQuestions(questionDao.readQuestions()));
    }

    public static void main(String[] args) throws IOException {
        App app = new App(args);
        app.execute();
    }
}
