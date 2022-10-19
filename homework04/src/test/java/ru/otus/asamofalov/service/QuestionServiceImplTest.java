package ru.otus.asamofalov.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.asamofalov.dao.QuestionDao;
import ru.otus.asamofalov.domain.ChosenAnswerQuestion;
import ru.otus.asamofalov.domain.FreeAnswerQuestion;
import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.helper.QuestionTransformer;
import ru.otus.asamofalov.io.IOService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = QuestionServiceImpl.class)
@DisplayName("QuestionServiceImpl class")
class QuestionServiceImplTest {

    @MockBean
    QuestionDao questionDao;

    @MockBean
    QuestionTransformer questionTransformer;

    @MockBean
    IOService ioService;

    @DisplayName("check for answers")
    @Test
    void shouldAskFreeFormQuestion() {

        Question question1 = new FreeAnswerQuestion("", "4");
        Question question2 = new ChosenAnswerQuestion("", "2", new String[0]);

        when(questionDao.getAll()).thenReturn(Arrays.asList(question1, question2));
        when(questionTransformer.asString(any())).thenReturn("");
        when(ioService.readStringWithFormattedPrompt(any(), any())).thenReturn("4").thenReturn("3");

        QuestionService questionService = new QuestionServiceImpl(
                questionDao,
                ioService,
                questionTransformer
        );
        var result = questionService.ask();
        assertAll(
                () -> assertTrue(result.get(0).isAnswerRight()),
                () -> assertFalse(result.get(1).isAnswerRight())
        );
    }
}