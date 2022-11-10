package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.QuestionEntity;
import ru.otus.spring.service.QuestionEntityServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {QuestionEntityServiceImpl.class, QuestionEntityDaoImpl.class})
public class QuestionEntityServiceImplTest {

    @Autowired
    private QuestionEntityServiceImpl qesi;

    @Test
    void getQuestion() {

        List<QuestionEntity> questions = qesi.getQuestion();
        assertEquals(5, questions.size());

        QuestionEntity question = questions.get(3);
        assertEquals("Когда родился Владимир Ильич Ульянов-Ленин?", question.getQuestionText());

        assertEquals(1, question.getCorrectAnswerNum());
    }
}