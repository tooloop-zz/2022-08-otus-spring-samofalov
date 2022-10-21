package ru.otus.asamofalov.helper;

import java.io.IOException;

public interface CsvParser {
    String[][] parse(String fileName) throws IOException;
}
