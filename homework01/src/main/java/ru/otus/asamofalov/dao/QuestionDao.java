package ru.otus.asamofalov.dao;

import ru.otus.asamofalov.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getAll();

}
