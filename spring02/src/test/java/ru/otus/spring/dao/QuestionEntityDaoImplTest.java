package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.QuestionEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest(classes = QuestionEntityDaoImpl.class)
public class QuestionEntityDaoImplTest {

    @Autowired
    private QuestionEntityDao questionEntityDao;
    @Value("${questions.filename}")
    String filename;

    @Test
    void getQuestions() {
        List<QuestionEntity> questions = questionEntityDao.getQuestions();
        assertEquals(5, questions.size());

        QuestionEntity question = questions.get(3);
        assertEquals("When was Lenin born?", question.getQuestionText());

        assertEquals(1, question.getCorrectAnswerNum());
    }
}
