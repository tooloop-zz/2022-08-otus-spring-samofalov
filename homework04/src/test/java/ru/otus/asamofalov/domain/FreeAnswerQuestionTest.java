package ru.otus.asamofalov.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("FreeAnswerQuestionTest class")
public class FreeAnswerQuestionTest {

    FreeAnswerQuestion freeAnswerQuestion;

    @BeforeEach
    void init() {
        freeAnswerQuestion = new FreeAnswerQuestion("question", "rightanswer");
    }

    @DisplayName("constructed correctly")
    @Test
    void shouldHaveCorrectConstructor() {
        assertAll("freeAnswerQuestion",
                () -> assertEquals("question", freeAnswerQuestion.getText()),
                () -> assertEquals("rightanswer", freeAnswerQuestion.getRightAnswer())
        );
    }

    @DisplayName("user right answer checked")
    @Test
    void shouldCheckForRightAnswer() {
        freeAnswerQuestion.setUserAnswer(freeAnswerQuestion.getRightAnswer());
        assertTrue(freeAnswerQuestion.isAnswerRight());
    }

    @DisplayName("user wrong answer checked")
    @Test
    void shouldCheckForWrongAnswer() {
        freeAnswerQuestion.setUserAnswer(freeAnswerQuestion.getRightAnswer() + "asldkj");
        assertFalse(freeAnswerQuestion.isAnswerRight());
    }
}
