package ru.otus.asamofalov.hw05.homework05.dao;

import ru.otus.asamofalov.hw05.homework05.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
}
