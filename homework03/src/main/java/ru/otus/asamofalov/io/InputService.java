package ru.otus.asamofalov.io;

public interface InputService {

    String readStringWithPrompt(String prompt);

    String readStringWithFormattedPrompt(String format, Object... args);
}
