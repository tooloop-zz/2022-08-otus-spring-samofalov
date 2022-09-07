package ru.otus.asamofalov.service;

import ru.otus.asamofalov.dao.QuestionDao;
import ru.otus.asamofalov.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAll() {
        return dao.getAll();
    }
}
