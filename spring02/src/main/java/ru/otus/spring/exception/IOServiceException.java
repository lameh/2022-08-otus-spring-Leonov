package ru.otus.spring.exception;

public class IOServiceException extends RuntimeException {
    public IOServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
