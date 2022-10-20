package ru.otus.asamofalov.helper;

import ru.otus.asamofalov.domain.Question;

public interface QuestionTransformer {
    String asString(Question question);
}
