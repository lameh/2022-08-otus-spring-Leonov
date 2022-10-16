package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionEntityDao;
import ru.otus.spring.domain.QuestionEntity;

import java.util.List;

@Service
public class QuestionEntityServiceImpl implements QuestionEntityService {

    private final QuestionEntityDao qed;

    public QuestionEntityServiceImpl(QuestionEntityDao qed) {
        this.qed = qed;
    }

    @Override
    public List<QuestionEntity> getQuestion() {
        return qed.getQuestions();
    }
}
