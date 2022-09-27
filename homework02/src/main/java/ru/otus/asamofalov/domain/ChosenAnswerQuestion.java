package ru.otus.asamofalov.domain;

public class ChosenAnswerQuestion extends Question {

    private final String[] answers;

    public ChosenAnswerQuestion(String text, String rightAnswer, String[] answers) {
        super(text, rightAnswer);
        this.answers = answers;
    }

    @Override
    public String asString() {

        String answers = "";
        for (String answer : this.answers) {
            answers += (!answers.isEmpty() ? ", " : "") + answer;
        }

        return text + " (choose your answer from list: " + answers + ")";
    }
}
