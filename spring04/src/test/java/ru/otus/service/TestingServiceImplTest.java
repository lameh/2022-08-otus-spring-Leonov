package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionEntity;
import ru.otus.spring.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = TestingServiceImpl.class)
public class TestingServiceImplTest {

    @Autowired
    private TestingService testingService;

    @MockBean
    private LocalizationService localizationService;
    @MockBean
    private QuestionEntityService qes;
    @MockBean
    private IOService ioService;

    private final List<QuestionEntity> questionEntities = new ArrayList<>();

    @BeforeEach
    void setUp() {
        var question = new Question(1, "Test?");
        var answer = new Answer("Test!");
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(answer);
        var questionEntity = new QuestionEntity(question,answers,1);
        questionEntities.add(questionEntity);
    }

    @Test
    void run() {
        doReturn(questionEntities).when(qes).getQuestion();
        doReturn("1").when(ioService).inputString();
        doReturn("---").when(localizationService).makeLocalized(anyString());
        doReturn("---").when(localizationService).makeLocalized(anyString(), anyInt());
        doReturn("---").when(localizationService).makeLocalized(anyString(), anyInt());
        doReturn("---").when(localizationService).makeLocalized(anyString(), anyInt());

        testingService.run();

        verify(qes, times(1)).getQuestion();
        verify(ioService, times(1)).inputString();
        verify(ioService, times(6)).outputString(anyString());
        verify(localizationService, times(1)).makeLocalized(anyString());
        verify(localizationService, times(3)).makeLocalized(anyString(), anyInt());
    }

}
