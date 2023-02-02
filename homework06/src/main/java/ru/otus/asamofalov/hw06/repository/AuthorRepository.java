package ru.otus.asamofalov.hw06.repository;

import ru.otus.asamofalov.hw06.domain.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> getAll();

    Author getByName(String name);

    Author append(Author author);
}
