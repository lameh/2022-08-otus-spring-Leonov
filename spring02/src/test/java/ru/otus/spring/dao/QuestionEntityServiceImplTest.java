package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.App;
import ru.otus.spring.domain.QuestionEntity;
import ru.otus.spring.service.QuestionEntityServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest(classes = App.class)
public class QuestionEntityServiceImplTest {

    @Autowired
    private QuestionEntityServiceImpl qesi;
    @Value("${questions.filename}")
    java.lang.String filename;

    @Test
    void getQuestions() {
        List<QuestionEntity> questions = qesi.getQuestion();
        assertEquals(5, questions.size());

        QuestionEntity question = questions.get(3);
        assertEquals("When was Lenin born?", question.getQuestionText());

        assertEquals(1, question.getCorrectAnswerNum());
    }
}
