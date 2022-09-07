package ru.otus.asamofalov.domain;

public class FreeAnswerQuestion extends Question {

    public FreeAnswerQuestion(String text) {
        super(text);
    }

    @Override
    public String asString() {
        return text + " (free form answer required)";
    }
}
