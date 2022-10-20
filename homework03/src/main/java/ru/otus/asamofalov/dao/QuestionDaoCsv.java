package ru.otus.asamofalov.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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

    public static final String ANSWERS_DELIMITER = "#";

    private final String fileName;
    private final CsvParser csvParser;
    private final String locale;

    public QuestionDaoCsv(@Value("${filename}") String fileName, @Value("${locale:en}") String locale, CsvParser csvParser) {
        this.fileName = fileName;
        this.csvParser = csvParser;
        this.locale = locale;
    }

    @Override
    public List<Question> getAll() throws DaoException {

        List<Question> questions = new ArrayList<>();

        try {
            for (String[] row : csvParser.parse(fileName)) {
                if (row.length > 0 && row[0] != null && row[0].equals(locale)) {
                    if (row.length > 2 && row[2].contains(ANSWERS_DELIMITER)) {
                        questions.add(new ChosenAnswerQuestion(row[1], row[3], row[2].split(ANSWERS_DELIMITER)));
                    } else {
                        questions.add(new FreeAnswerQuestion(row[1], row[3]));
                    }
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
