package ru.otus.asamofalov.dao;

import org.junit.jupiter.api.Test;

public class QuestionDaoCsvTest {

    @Test
    public void testQuestionCount() throws Exception {
        QuestionDao dao = new QuestionDaoCsv("questions2.csv");
        if (dao.getAll().size() != 2) throw new Exception("wrong questions count");
        System.out.println("test OK");
    }
}
