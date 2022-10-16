package ru.otus.spring.domain;

public class ResponseEntity {

    private final int questionNum;
    private final int answerNum;

    public ResponseEntity(int questionNum, int answerNum) {
        this.questionNum = questionNum;
        this.answerNum = answerNum;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public int getAnswerNum() {
        return answerNum;
    }
}
