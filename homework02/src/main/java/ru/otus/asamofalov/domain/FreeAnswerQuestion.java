package ru.otus.asamofalov.domain;

public class FreeAnswerQuestion extends Question {

    public FreeAnswerQuestion(String text, String rightAnswer) {
        super(text, rightAnswer);
    }

    @Override
    public String asString() {
        return text + " (free form answer required)";
    }
}
