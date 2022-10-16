package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.QuestionEntity;
import ru.otus.spring.domain.ResponseEntity;
import ru.otus.spring.domain.TestingResult;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService{
    private final IOService ioService;

    private final QuestionEntityService questionEntityService;

    public TestingServiceImpl(IOService ioService, QuestionEntityService questionEntityService) {
        this.ioService = ioService;
        this.questionEntityService = questionEntityService;
    }

    @Override
    public void run() {
        List<QuestionEntity> questions = questionEntityService.getQuestion();
        int corransw = 0;
        for (QuestionEntity question:questions) {
            ioService.outputString(question.getQuestionText());
            for (int i = 0; i < question.getAnswers().size(); i++) {
                String answ = question.getAnswers().get(i).getText();
                ioService.outputString((i+1) + ". " + answ);
            }
            int respNum = Integer.parseInt(ioService.inputString());
            ResponseEntity response = new ResponseEntity(question.getQuestionNum(), respNum);
            if (response.getAnswerNum() == question.getCorrectAnswerNum()) {
                corransw = corransw + 1;
            }
        }
        TestingResult result = new TestingResult(questions.size(), corransw);

        testingResult(result);
    }

    private void testingResult (TestingResult result) {
        ioService.outputString("Your result: " + result.getTotalAnswers() + " total questions, "
        + result.getCorrectAnswers() + " correct answers. Your score is "
                + (int)((float)result.getCorrectAnswers() / (float)result.getTotalAnswers() * 100) + " %");
    }
}
