package ru.otus.asamofalov.hw08.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.asamofalov.hw08.domain.Author;
import ru.otus.asamofalov.hw08.domain.Book;
import ru.otus.asamofalov.hw08.domain.BookComment;
import ru.otus.asamofalov.hw08.domain.Genre;
import ru.otus.asamofalov.hw08.helper.FormattedList;
import ru.otus.asamofalov.hw08.repository.AuthorRepository;
import ru.otus.asamofalov.hw08.repository.BookCommentRepository;
import ru.otus.asamofalov.hw08.repository.BookRepository;
import ru.otus.asamofalov.hw08.repository.GenreRepository;

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
    public Book getBook(String id) {
        return bookRepository.findById(id).get();
    }

    @Override
    @ShellMethod(value = "append new book", key = {"c", "create"})
    public Book appendBook(String title, String authorName, String genreName) {
        return bookRepository.save(new Book(title, findOrAppendAuthor(authorName), findOrAppendGenre(genreName)));
    }

    @Override
    @ShellMethod(value = "update book title by id", key = {"u", "update"})
    public Book updateBook(String id, String title) {
        Book book = bookRepository.findById(id).get();
        book.setTitle(title);
        return bookRepository.save(book);
    }

    @Override
    @ShellMethod(value = "delete book by id", key = {"d", "delete"})
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    @ShellMethod(value = "get comments list by bookId", key = {"lc", "list-comments"})
    public List<BookComment> getAllComments(String bookId) {
        return new FormattedList<>(bookRepository.findById(bookId).get().getComments());
    }

    @Override
    @ShellMethod(value = "get comment by id", key = {"rc", "read-comment"})
    public BookComment getComment(String commentId) {
        return bookCommentRepository.findById(commentId).get();
    }

    @Override
    @ShellMethod(value = "append comment for book", key = {"cc", "create-comment"})
    public BookComment appendComment(String bookId, String text) {
        Book book = bookRepository.findById(bookId).get();
        BookComment bookComment = new BookComment(text);
        bookComment = bookCommentRepository.save(bookComment);
        book.getComments().add(bookComment);
        bookRepository.save(book);
        return bookComment;
    }

    @Override
    @ShellMethod(value = "update comment for book", key = {"uc", "update-comment"})
    public BookComment updateComment(String commentId, String text) {
        BookComment bookComment = bookCommentRepository.findById(commentId).get();
        bookComment.setText(text);
        return bookCommentRepository.save(bookComment);
    }

    @Override
    @ShellMethod(value = "delete book's comment", key = {"dc", "delete-comment"})
    public void deleteComment(String commentId) {
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