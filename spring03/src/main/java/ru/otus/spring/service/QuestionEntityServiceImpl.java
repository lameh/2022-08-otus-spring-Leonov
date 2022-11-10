package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionEntityDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionEntity;
import ru.otus.spring.exception.MyTestingException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class QuestionEntityServiceImpl implements QuestionEntityService {

    private final QuestionEntityDao qed;

    private final LocalizationService localizationService;

    public QuestionEntityServiceImpl(QuestionEntityDao qed, LocalizationService localizationService) {
        this.qed = qed;
        this.localizationService = localizationService;
    }

    @Override
    public List<QuestionEntity> getQuestion() {
        List<QuestionEntity> questions = new ArrayList<>();
        Iterator<String> iterator = qed.readQuestions().iterator();

        while (iterator.hasNext()) {
            String[] str = iterator.next().split(";");
            int num;
            try {
                num = Integer.parseInt(str[0]);
            } catch (NumberFormatException e) {
                throw new MyTestingException(localizationService.makeLocalized("number.questions"), e);
            }
            var text = str[1];
            var question = new Question(num, text);
            int correctAnswerNum;
            try {
                correctAnswerNum = Integer.parseInt(str[2]);
            } catch (NumberFormatException e) {
                throw new MyTestingException(localizationService.makeLocalized("number.answers"), e);
            }
            ArrayList<Answer> answ = new ArrayList<>();
            for (int i = 3; i < str.length; i++) {
                answ.add(new Answer(str[i]));
            }
            questions.add(new QuestionEntity(question, answ, correctAnswerNum));
        }
        return questions;
    }
}
