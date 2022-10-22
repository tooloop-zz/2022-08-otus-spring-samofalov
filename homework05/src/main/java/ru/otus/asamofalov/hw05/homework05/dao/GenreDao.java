package ru.otus.asamofalov.hw05.homework05.dao;

import ru.otus.asamofalov.hw05.homework05.domain.Genre;
import ru.otus.asamofalov.hw05.homework05.helper.OverridedList;

public interface GenreDao {

    long getIdByName(String name);

    long insertGenre(Genre genre);

    OverridedList<Genre> getAll();
}
