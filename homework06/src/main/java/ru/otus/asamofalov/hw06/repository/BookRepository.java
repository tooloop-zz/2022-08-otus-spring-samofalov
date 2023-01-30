package ru.otus.asamofalov.hw06.repository;

import ru.otus.asamofalov.hw06.domain.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    Book getById(long id);

    Book getByIdWithComments(long id);

    void delete(Book book);

    Book append(Book book);

    Book update(Book book);
}
