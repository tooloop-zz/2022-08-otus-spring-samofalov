package ru.otus.asamofalov.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.asamofalov.exception.DaoException;
import ru.otus.asamofalov.helper.CsvParserImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("QuestionDaoCsv class")
class QuestionDaoCsvTest {

    @DisplayName("check wrong file name exception")
    @Test
    void shouldRaiseException() {
        QuestionDaoCsv dao = new QuestionDaoCsv("questions2.csv", "en", new CsvParserImpl());
        assertAll(
                () -> {
                    DaoException daoException = assertThrows(DaoException.class, () -> dao.getAll());
                    assertTrue(daoException.getMessage().contains("CSV parsing error"));
                }
        );
    }

    @DisplayName("check questions count")
    @Test
    void shouldCheckQuestionsCount() {
        QuestionDaoCsv dao = new QuestionDaoCsv("questions_test03.csv", "en", new CsvParserImpl());
        assertEquals(3, dao.getAll().size());
    }
}