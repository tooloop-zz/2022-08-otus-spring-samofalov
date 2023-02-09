package ru.otus.asamofalov.hw08.service;

import ru.otus.asamofalov.hw08.domain.Author;
import ru.otus.asamofalov.hw08.domain.Book;
import ru.otus.asamofalov.hw08.domain.BookComment;
import ru.otus.asamofalov.hw08.domain.Genre;

import java.util.List;

public interface BookService {

    List<Author> getAllAuthors();

    List<Genre> getAllGenres();

    List<Book> getAll();

    Book getBook(String id);

    Book appendBook(String title, String author, String genre);

    Book updateBook(String id, String title);

    void deleteBook(String id);

    List<BookComment> getAllComments(String bookId);

    BookComment getComment(String commentId);

    BookComment appendComment(String bookId, String text);

    BookComment updateComment(String commentId, String text);

    void deleteComment(String bookCommentId);
}