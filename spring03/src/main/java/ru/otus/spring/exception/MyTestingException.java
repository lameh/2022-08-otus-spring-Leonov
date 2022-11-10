package ru.otus.spring.exception;

public class MyTestingException extends RuntimeException {

    public MyTestingException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

