package ru.otus.asamofalov.service;

public interface OutputService {

    void outputString(String s);

    void outputFormattedString(String format, Object[] args);
}
