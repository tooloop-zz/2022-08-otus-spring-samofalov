package ru.otus.asamofalov.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ChosenAnswerQuestionTest class")
class ChosenAnswerQuestionTest {

    @DisplayName("answer variants array not empty")
    @Test
    void shouldCheckAnswerVariantsSize() {
        ChosenAnswerQuestion chosenAnswerQuestion =
                new ChosenAnswerQuestion("question", "answer", new String[]{"answer1", "answer2", "answer3"});
        assertAll(
                () -> assertNotNull(chosenAnswerQuestion.getAnswers()),
                () -> assertEquals(3, chosenAnswerQuestion.getAnswers().length)
        );
    }

}