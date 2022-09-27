package ru.otus.asamofalov.service;

import org.springframework.stereotype.Service;
import ru.otus.asamofalov.dao.QuestionDao;
import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.exception.QuestionServiceException;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAll() throws QuestionServiceException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new QuestionServiceException(e);
        }
    }
}
