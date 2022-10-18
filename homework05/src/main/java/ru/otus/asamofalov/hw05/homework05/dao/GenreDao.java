package ru.otus.asamofalov.hw05.homework05.dao;

import ru.otus.asamofalov.hw05.homework05.domain.Genre;

public interface GenreDao {

    long getIdByName(String name);

    long insertGenre(Genre genre);
}
