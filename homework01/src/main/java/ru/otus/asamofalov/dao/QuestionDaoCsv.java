package ru.otus.asamofalov.dao;

import ru.otus.asamofalov.Constants;
import ru.otus.asamofalov.domain.ChosenAnswerQuestion;
import ru.otus.asamofalov.domain.FreeAnswerQuestion;
import ru.otus.asamofalov.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoCsv implements QuestionDao {

    private static final List<Question> questions = new ArrayList<>();

    public QuestionDaoCsv(String filename) {
        parseData(filename);
    }

    private void parseData(String fileName) {
        try {

            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);

            try (InputStreamReader streamReader =
                         new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] items = line.split(";");
                    if (items.length > 1 && items[1].contains(Constants.ANSWERS_DELIMITER)) {
                        questions.add(new ChosenAnswerQuestion(items[0], items[1]));
                    } else {
                        questions.add(new FreeAnswerQuestion(items[0]));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("input CSV parsing error: " + e.getMessage());
        }
    }

    @Override
    public List<Question> getAll() {
        return questions;
    }
}
