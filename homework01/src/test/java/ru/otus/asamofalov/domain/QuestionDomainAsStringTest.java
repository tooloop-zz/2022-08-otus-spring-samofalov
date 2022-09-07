package ru.otus.asamofalov.domain;

import org.junit.Test;

public class QuestionDomainAsStringTest {

    @Test
    public void testAsString() throws Exception {
        ChosenAnswerQuestion question = new ChosenAnswerQuestion("question", "a1#a2#a03");
        String text = question.asString();
        System.out.println(text);
        if (!text.contains("3.")) throw new Exception("answers count less than 3");
        System.out.println("test OK");
    }
}
