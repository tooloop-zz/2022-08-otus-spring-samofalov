package ru.otus.asamofalov.dao;

import ru.otus.asamofalov.domain.ChosenAnswerQuestion;
import ru.otus.asamofalov.domain.FreeAnswerQuestion;
import ru.otus.asamofalov.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDaoSimple implements QuestionDao {
    @Override
    public List<Question> getAll() {
        List<Question> result = new ArrayList<>();
        result.add(new FreeAnswerQuestion("Is this a free form question?"));
        result.add(new ChosenAnswerQuestion("Is this a question with choice?", "yes#no"));
        return result;
    }
}
