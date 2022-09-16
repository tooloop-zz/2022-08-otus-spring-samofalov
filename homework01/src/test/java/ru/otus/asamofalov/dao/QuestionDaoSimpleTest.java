package ru.otus.asamofalov.dao;

import org.junit.Test;

public class QuestionDaoSimpleTest {

    @Test
    public void testQuestionCount() throws Exception {
        QuestionDao dao = new QuestionDaoSimple();
        if (dao.getAll().size() != 2) throw new Exception("wrong questions count");
        System.out.println("test OK");
    }

}
