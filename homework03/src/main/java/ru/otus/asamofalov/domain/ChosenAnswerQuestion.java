package ru.otus.asamofalov.domain;

public class ChosenAnswerQuestion extends Question {

    private final String[] answers;

    public ChosenAnswerQuestion(String text, String rightAnswer, String[] answers) {
        super(text, rightAnswer);
        this.answers = answers;
    }

    public String[] getAnswers() {
        return answers;
    }
}
