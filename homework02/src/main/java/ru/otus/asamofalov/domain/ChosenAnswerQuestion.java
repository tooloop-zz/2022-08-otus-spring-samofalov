package ru.otus.asamofalov.domain;

import ru.otus.asamofalov.Constants;

import java.util.ArrayList;
import java.util.List;

public class ChosenAnswerQuestion extends Question {

    private final List<String> answers = new ArrayList<>();

    public ChosenAnswerQuestion(String text, String rightAnswer, String answers) {
        super(text, rightAnswer);
        parseAnswers(answers);
    }

    private void parseAnswers(String answersLine) {
        try {
            for (String answer : answersLine.split(Constants.ANSWERS_DELIMITER)) {
                answers.add(answer);
            }
        } catch (Exception e) {
            System.out.println("Answers parsing error: " + e.getMessage());
        }
    }

    @Override
    public String asString() {
        return text + " (choose your answer from list:" + getAnswers() + ")";
    }

    private String getAnswers() {
        String result = "";

        for (int i = 0; i < answers.size(); i++) {
            result += " " + (i == 0 ? "" : ",") + answers.get(i);
        }

        return result;
    }
}
