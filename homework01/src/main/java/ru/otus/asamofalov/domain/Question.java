package ru.otus.asamofalov.domain;

public abstract class Question {

    protected final String text;

    public Question(String text) {
        this.text = text;
    }

    public abstract String asString();
}
