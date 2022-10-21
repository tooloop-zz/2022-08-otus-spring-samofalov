package ru.otus.asamofalov.service;

public interface AppMessageSource {

    String getMessage(String code, Object... args);
}
