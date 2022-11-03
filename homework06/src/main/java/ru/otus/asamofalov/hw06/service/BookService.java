package ru.otus.asamofalov.hw06.service;

import ru.otus.asamofalov.hw06.domain.Author;
import ru.otus.asamofalov.hw06.domain.Book;
import ru.otus.asamofalov.hw06.domain.BookComment;
import ru.otus.asamofalov.hw06.domain.Genre;
import ru.otus.asamofalov.hw06.helper.FormattedList;

import java.util.List;

public interface BookService {

    FormattedList<Author> getAllAuthors();

    FormattedList<Genre> getAllGenres();

    List<Book> getAll();

    Book getBook(long id);

    Book appendBook(String title, String author, String genre);

    Book updateBook(long id, String title);

    void deleteBook(long id);

    FormattedList<BookComment> getAllComments(long bookId);

    BookComment getComment(long commentId);

    BookComment appendComment(long bookId, String text);

    void deleteComment(long bookCommentId);

    BookComment updateComment(long bookCommentId, String text);
}
