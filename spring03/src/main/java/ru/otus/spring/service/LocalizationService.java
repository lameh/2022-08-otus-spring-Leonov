package ru.otus.spring.service;

public interface LocalizationService {

    String makeLocalized(String message, Object ... objects);
}
