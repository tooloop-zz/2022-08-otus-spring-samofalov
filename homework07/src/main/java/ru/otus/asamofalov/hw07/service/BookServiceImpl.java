package ru.otus.asamofalov.hw07.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.asamofalov.hw07.domain.Author;
import ru.otus.asamofalov.hw07.domain.Book;
import ru.otus.asamofalov.hw07.domain.BookComment;
import ru.otus.asamofalov.hw07.domain.Genre;
import ru.otus.asamofalov.hw07.helper.FormattedList;
import ru.otus.asamofalov.hw07.repository.AuthorRepository;
import ru.otus.asamofalov.hw07.repository.BookCommentRepository;
import ru.otus.asamofalov.hw07.repository.BookRepository;
import ru.otus.asamofalov.hw07.repository.GenreRepository;

import java.util.List;

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
    public List<Author> getAllAuthors() {
        return new FormattedList<>(authorRepository.findAll());
    }

    @Override
    @ShellMethod(value = "get genres list", key = {"lg", "list-genres"})
    public List<Genre> getAllGenres() {
        return new FormattedList<>(genreRepository.findAll());
    }

    @Override
    @ShellMethod(key = {"l", "list-books"}, value = "get books list")
    public List<Book> getAll() {
        return new FormattedList<>(bookRepository.findAll());
    }

    @Override
    @ShellMethod(value = "get book by id", key = {"r", "read"})
    public Book getBook(long id) {
        return bookRepository.findById(id);
    }

    @Override
    @ShellMethod(value = "append new book", key = {"c", "create"})
    @Transactional
    public Book appendBook(String title, String authorName, String genreName) {
        return bookRepository.save(new Book(title, findOrAppendAuthor(authorName), findOrAppendGenre(genreName)));
    }

    @Override
    @ShellMethod(value = "update book title by id", key = {"u", "update"})
    @Transactional
    public Book updateBook(long id, String title) {
        Book book = bookRepository.findById(id);
        book.setTitle(title);
        return bookRepository.save(book);
    }

    @Override
    @ShellMethod(value = "delete book by id", key = {"d", "delete"})
    @Transactional
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @ShellMethod(value = "get comments list by bookId", key = {"lc", "list-comments"})
    @Transactional(readOnly = true)
    public List<BookComment> getAllComments(long bookId) {
        return new FormattedList<>(bookRepository.findById(bookId).getComments());
    }

    @Override
    @ShellMethod(value = "get comment by id", key = {"rc", "read-comment"})
    public BookComment getComment(long commentId) {
        return bookCommentRepository.findById(commentId);
    }

    @Override
    @ShellMethod(value = "append comment for book", key = {"cc", "create-comment"})
    @Transactional
    public BookComment appendComment(long bookId, String text) {
        return bookCommentRepository.save(new BookComment(bookId, text));
    }

    @Override
    @ShellMethod(value = "update comment for book", key = {"uc", "update-comment"})
    @Transactional
    public BookComment updateComment(long commentId, String text) {
        BookComment bookComment = bookCommentRepository.findById(commentId);
        if (bookComment == null) return null;
        bookComment.setText(text);
        return bookCommentRepository.save(bookComment);
    }

    @Override
    @ShellMethod(value = "delete book's comment", key = {"dc", "delete-comment"})
    @Transactional
    public void deleteComment(long commentId) {
        bookCommentRepository.deleteById(commentId);
    }

    private Author findOrAppendAuthor(String authorName) {
        Author author = authorRepository.findByName(authorName);
        if (author == null) author = authorRepository.save(new Author(authorName));
        return author;
    }

    private Genre findOrAppendGenre(String genreName) {
        Genre genre = genreRepository.findByName(genreName);
        if (genre == null) genre = genreRepository.save(new Genre(genreName));
        return genre;
    }
}