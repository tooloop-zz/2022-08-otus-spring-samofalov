package ru.otus.asamofalov.hw06.repository;

import ru.otus.asamofalov.hw06.domain.Author;
import ru.otus.asamofalov.hw06.helper.FormattedList;

public interface AuthorRepository {

    FormattedList<Author> getAll();

    Author getByName(String name);

    Author appendAuthor(Author author);
}
