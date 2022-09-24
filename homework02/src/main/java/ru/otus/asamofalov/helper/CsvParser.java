package ru.otus.asamofalov.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class CsvParser {

    public static String[][] parse(String fileName) throws IOException {

        List<String[]> result = new ArrayList<>();

        for (String line : getLines(fileName)) {
            String[] items = line.split(";");
            result.add(items);
        }

        return result.toArray(new String[0][0]);
    }

    private static String[] getLines(String fileName) throws IOException {

        List<String> result = new ArrayList<>();

        ClassLoader classLoader = CsvParser.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        }

        return result.toArray(new String[0]);
    }
}
