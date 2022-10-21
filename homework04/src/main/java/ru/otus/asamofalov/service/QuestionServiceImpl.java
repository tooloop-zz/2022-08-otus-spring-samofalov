package ru.otus.asamofalov.service;

import org.springframework.stereotype.Service;
import ru.otus.asamofalov.dao.QuestionDao;
import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.exception.QuestionServiceException;
import ru.otus.asamofalov.helper.QuestionTransformer;
import ru.otus.asamofalov.io.IOService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;
    private final IOService ioService;
    private final QuestionTransformer questionTransformer;

    public QuestionServiceImpl(QuestionDao dao, IOService ioService, QuestionTransformer questionTransformer) {
        this.dao = dao;
        this.ioService = ioService;
        this.questionTransformer = questionTransformer;
    }

    @Override
    public List<Question> ask() {
        try {
            List<Question> questions = dao.getAll();
            for (Question question : questions) {
                question.setUserAnswer(ioService.readStringWithFormattedPrompt(
                        "%d. %s: ",
                        questions.indexOf(question) + 1,
                        questionTransformer.asString(question)
                ));
            }
            return questions;
        } catch (Exception e) {
            throw new QuestionServiceException(e);
        }
    }
}
