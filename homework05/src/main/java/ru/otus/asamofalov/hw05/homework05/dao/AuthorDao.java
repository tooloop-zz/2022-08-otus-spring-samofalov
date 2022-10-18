package ru.otus.asamofalov.hw05.homework05.dao;

import ru.otus.asamofalov.hw05.homework05.domain.Author;

public interface AuthorDao {

    Author getByName(String name);
}
