package ru.otus.asamofalov.hw05.homework05.dao;

import ru.otus.asamofalov.hw05.homework05.domain.Author;
import ru.otus.asamofalov.hw05.homework05.helper.OverridedList;

public interface AuthorDao {

    long getIdByName(String name);

    long insertAuthor(Author author);

    OverridedList<Author> getAll();
}
