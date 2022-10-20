package ru.otus.asamofalov.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.asamofalov.helper.CsvParser;
import ru.otus.asamofalov.helper.CsvParserImpl;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CsvParserImpl class")
class CsvParserImplTest {

    @Test
    @DisplayName("should parse CSV and check lines count")
    void shouldParseAndCheckLinesCount() {
        CsvParser csvParser = new CsvParserImpl();
        String fileName = "questions_test01.csv";
        assertAll(
                () -> assertDoesNotThrow(() -> Arrays.asList(csvParser.parse(fileName)).forEach((r) -> System.out.println(String.join("\t", r)))),
                () -> assertEquals(3, csvParser.parse(fileName).length)
        );
    }
}