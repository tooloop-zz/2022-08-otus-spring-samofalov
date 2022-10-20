package ru.otus.asamofalov.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AppMessageSourceImpl implements AppMessageSource {

    private final MessageSource messageSource;
    private final Locale locale;


    public AppMessageSourceImpl(MessageSource messageSource, @Value("${locale:en}") Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, locale);
    }
}
