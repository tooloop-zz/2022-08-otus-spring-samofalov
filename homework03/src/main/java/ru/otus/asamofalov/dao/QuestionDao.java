package ru.otus.asamofalov.dao;

import ru.otus.asamofalov.domain.Question;
import ru.otus.asamofalov.exception.DaoException;

import java.util.List;

public interface QuestionDao {

    List<Question> getAll() throws DaoException;
}
