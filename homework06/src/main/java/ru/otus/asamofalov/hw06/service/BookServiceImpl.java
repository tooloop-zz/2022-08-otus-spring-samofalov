package ru.otus.asamofalov.hw06.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.asamofalov.hw06.domain.Author;
import ru.otus.asamofalov.hw06.domain.Book;
import ru.otus.asamofalov.hw06.domain.BookComment;
import ru.otus.asamofalov.hw06.domain.Genre;
import ru.otus.asamofalov.hw06.exception.BookNotFoundException;
import ru.otus.asamofalov.hw06.helper.FormattedList;
import ru.otus.asamofalov.hw06.repository.AuthorRepository;
import ru.otus.asamofalov.hw06.repository.BookCommentRepository;
import ru.otus.asamofalov.hw06.repository.BookRepository;
import ru.otus.asamofalov.hw06.repository.GenreRepository;

@ShellComponent
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookCommentRepository bookCommentRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, BookCommentRepository bookCommentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookCommentRepository = bookCommentRepository;
    }

    @Override
    @ShellMethod(value = "get authors list", key = {"la", "list-authors"})
    public FormattedList<Author> getAllAuthors() {
        return new FormattedList<>(authorRepository.getAll());
    }

    @Override
    @ShellMethod(value = "get genres list", key = {"lg", "list-genres"})
    public FormattedList<Genre> getAllGenres() {
        return new FormattedList<>(genreRepository.getAll());
    }

    @Override
    @ShellMethod(key = {"l", "list-books"}, value = "get books list")
    public FormattedList<Book> getAll() {
        return new FormattedList<>(bookRepository.getAll());
    }

    @Override
    @ShellMethod(value = "append new book", key = {"c", "create"})
    @Transactional
    public Book appendBook(String title, String authorName, String genreName) {
        return bookRepository.append(new Book(title, new Author(authorName), new Genre(genreName)));
    }

    @Override
    @ShellMethod(value = "get book by id", key = {"r", "read"})
    public Book getBook(long id) {
        return bookRepository.getById(id);
    }

    @Override
    @ShellMethod(value = "update book title by id", key = {"u", "update"})
    @Transactional
    public Book updateBook(long id, String title) {
        Book book = bookRepository.getById(id);
        if (book == null) {
            throw new BookNotFoundException();
        }
        book.setTitle(title);
        return bookRepository.update(book);
    }

    @Override
    @ShellMethod(value = "delete book by id", key = {"d", "delete"})
    @Transactional
    public void deleteBook(long id) {
        Book book = bookRepository.getById(id);
        if (book == null) {
            throw new BookNotFoundException();
        }
        bookRepository.delete(book);
    }

    @Override
    @ShellMethod(value = "get comments list by bookId", key = {"lc", "list-comments"})
    public FormattedList<BookComment> getAllComments(long bookId) {
        return new FormattedList<>(bookRepository.getByIdWithComments(bookId).getComments());
    }

    @Override
    @ShellMethod(value = "get comment by id", key = {"rc", "read-comment"})
    public BookComment getComment(long commentId) {
        return bookCommentRepository.getById(commentId);
    }

    @Override
    @ShellMethod(value = "append comment for book", key = {"cc", "create-comment"})
    @Transactional
    public BookComment appendComment(long bookId, String text) {
        BookComment bookComment = new BookComment(text, bookId);
        return bookCommentRepository.append(bookComment);
    }

    @Override
    @ShellMethod(value = "update comment for book", key = {"uc", "update-comment"})
    @Transactional
    public BookComment updateComment(long commentId, String text) {
        BookComment bookComment = bookCommentRepository.getById(commentId);
        bookComment.setText(text);
        return bookCommentRepository.update(bookComment);
    }

    @Override
    @ShellMethod(value = "delete book's comment", key = {"dc", "delete-comment"})
    @Transactional
    public void deleteComment(long commentId) {
        BookComment bookComment = bookCommentRepository.getById(commentId);
        bookCommentRepository.delete(bookComment);
    }
}