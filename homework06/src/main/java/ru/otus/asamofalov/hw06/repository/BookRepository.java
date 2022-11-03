package ru.otus.asamofalov.hw06.repository;

import ru.otus.asamofalov.hw06.domain.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    Book getBook(long id);

    void deleteBook(Book book);

    Book appendBook(Book book);

    Book updateBook(Book book);
}
