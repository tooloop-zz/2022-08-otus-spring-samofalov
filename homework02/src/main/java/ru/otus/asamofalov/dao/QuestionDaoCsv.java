package ru.otus.asamofalov.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.asamofalov.Constants;
import ru.otus.asamofalov.domain.ChosenAnswerQuestion;
import ru.otus.asamofalov.domain.FreeAnswerQuestion;
import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.exception.DaoException;
import ru.otus.asamofalov.helper.CsvParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDaoCsv implements QuestionDao {

    private final String fileName;

    public QuestionDaoCsv(@Value("${filename}") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> getAll() throws DaoException {

        List<Question> questions = new ArrayList<>();

        try {
            for (String[] row : CsvParser.parse(fileName)) {

                if (row.length > 1 && row[1].contains(Constants.ANSWERS_DELIMITER)) {
                    questions.add(new ChosenAnswerQuestion(row[0], row[2], row[1]));
                } else {
                    questions.add(new FreeAnswerQuestion(row[0], row[2]));
                }
            }
        } catch (IOException e) {
            throw new DaoException("CSV IO error: " + fileName, e);
        } catch (Exception e) {
            throw new DaoException("CSV parsing error", e);
        }

        return questions;
    }
}
