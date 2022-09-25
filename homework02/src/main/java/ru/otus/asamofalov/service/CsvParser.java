package ru.otus.asamofalov.service;

import java.io.IOException;

public interface CsvParser {
    String[][] parse(String fileName) throws IOException;
}
