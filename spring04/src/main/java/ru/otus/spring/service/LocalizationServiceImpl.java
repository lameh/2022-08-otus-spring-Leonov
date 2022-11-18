package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;
    private final Locale locale;

    public LocalizationServiceImpl(MessageSource messageSource, @Value("${app.current-locale}") Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public String makeLocalized(String message, Object... objects) {
        return messageSource.getMessage(message, objects, locale);
    }
}
