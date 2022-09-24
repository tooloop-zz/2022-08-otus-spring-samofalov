package ru.otus.asamofalov.service;

import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.exception.QuestionServiceException;

import java.util.List;

public interface QuestionService {

    List<Question> getAll() throws QuestionServiceException;
}
