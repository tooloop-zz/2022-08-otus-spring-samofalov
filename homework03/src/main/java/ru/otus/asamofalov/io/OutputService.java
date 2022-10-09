package ru.otus.asamofalov.io;

public interface OutputService {

    void outputString(String s);

    void outputFormattedString(String format, Object... args);

    void outputStringLn(String s);
}
