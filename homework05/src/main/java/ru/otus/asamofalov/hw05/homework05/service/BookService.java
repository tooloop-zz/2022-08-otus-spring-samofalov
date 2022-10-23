package ru.otus.asamofalov.hw05.homework05.service;

import ru.otus.asamofalov.hw05.homework05.domain.Author;
import ru.otus.asamofalov.hw05.homework05.domain.Book;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;
import ru.otus.asamofalov.hw05.homework05.helper.OverridedList;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    String appendBook(String title, String author, String genre);

    Book getBook(String title, String author);

    String updateBook(String title, String author, String genre);

    String deleteBook(String title, String author);

    OverridedList<Author> getAllAuthors();

    OverridedList<Genre> getAllGenres();
}
