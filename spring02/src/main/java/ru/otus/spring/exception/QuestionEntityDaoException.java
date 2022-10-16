package ru.otus.spring.exception;

public class QuestionEntityDaoException extends RuntimeException {

    public QuestionEntityDaoException (final String message, final Throwable cause) {
        super(message, cause);
    }
}

