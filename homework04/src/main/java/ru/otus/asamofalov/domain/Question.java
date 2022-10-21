package ru.otus.asamofalov.domain;

public abstract class Question {

    protected final String text;
    protected final String rightAnswer;
    private String userAnswer;

    public Question(String text, String rightAnswer) {
        this.text = text;
        this.rightAnswer = rightAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public boolean isAnswerRight() {
        return rightAnswer != null && rightAnswer.equalsIgnoreCase(userAnswer);
    }

    public String getText() {
        return text;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
