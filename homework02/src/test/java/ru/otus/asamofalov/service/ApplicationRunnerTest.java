package ru.otus.asamofalov.service;

import org.junit.jupiter.api.Test;
import ru.otus.asamofalov.dao.QuestionDaoCsv;

public class ApplicationRunnerTest {

    @Test
    public void ioServiceTest() {

        try {
            new ApplicationRunner(new QuestionServiceImpl(new QuestionDaoCsv("questions2.scv")), 2).
                    run(null);
        } catch (Exception e) {
            System.out.println("null ioServiceTest ok");
        }
    }
}
