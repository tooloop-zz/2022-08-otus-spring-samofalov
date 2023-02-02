package ru.otus.asamofalov.hw06.repository;

import ru.otus.asamofalov.hw06.domain.Genre;
import ru.otus.asamofalov.hw06.helper.FormattedList;

public interface GenreRepository {

    FormattedList<Genre> getAll();

    Genre getByName(String name);

    Genre append(Genre genre);
}
