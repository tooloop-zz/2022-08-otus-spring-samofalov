package ru.otus.asamofalov.hw07.service;

import ru.otus.asamofalov.hw07.domain.Author;
import ru.otus.asamofalov.hw07.domain.Book;
import ru.otus.asamofalov.hw07.domain.BookComment;
import ru.otus.asamofalov.hw07.domain.Genre;

import java.util.List;

public interface BookService {

    List<Author> getAllAuthors();

    List<Genre> getAllGenres();

    List<Book> getAll();

    Book getBook(long id);

    Book appendBook(String title, String author, String genre);

    Book updateBook(long id, String title);

    void deleteBook(long id);

    List<BookComment> getAllComments(long bookId);

    BookComment getComment(long commentId);

    BookComment appendComment(long bookId, String text);

    BookComment updateComment(long commentId, String text);

    void deleteComment(long bookCommentId);
}