package ru.otus.spring.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class QuestionEntity {

    private final Question question;
    private final ArrayList<Answer> answers;
    private final int correctAnswerNum;

    public String getQuestionText() {
        return question.getText();
    }

    public int getQuestionNum() {
        return question.getNum();
    }
}
