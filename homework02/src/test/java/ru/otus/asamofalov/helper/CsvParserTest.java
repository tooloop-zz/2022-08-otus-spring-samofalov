package ru.otus.asamofalov.helper;

import org.junit.jupiter.api.Test;
import ru.otus.asamofalov.service.CsvParserImpl;

public class CsvParserTest {

    @Test
    public void parseTest() throws Exception {
        for (String[] rowCells : new CsvParserImpl().parse("questions2.csv")) {
            if (rowCells.length != 3) throw new Exception("cell count not equal 3");
        }
        System.out.println("parseTest ok");
    }
}
