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
    @Transactional(readOnly = true)
    public FormattedList<Author> getAllAuthors() {
        return authorRepository.getAll();
    }

    @Override
    @ShellMethod(value = "get genres list", key = {"lg", "list-genres"})
    @Transactional(readOnly = true)
    public FormattedList<Genre> getAllGenres() {
        return genreRepository.getAll();
    }

    @Override
    @ShellMethod(key = {"l", "list-books"}, value = "get books list")
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    @ShellMethod(value = "append new book", key = {"c", "create"})
    @Transactional
    public Book appendBook(String title, String authorName, String genreName) {
        Author author = authorRepository.appendAuthor(new Author(authorName));
        Genre genre = genreRepository.appendGenre(new Genre(genreName));
        return bookRepository.appendBook(new Book(title, author, genre));
    }

    @Override
    @ShellMethod(value = "get book by id", key = {"r", "read"})
    @Transactional(readOnly = true)
    public Book getBook(long id) {
        return bookRepository.getBook(id);
    }

    @Override
    @ShellMethod(value = "update book title by id", key = {"u", "update"})
    @Transactional
    public Book updateBook(long id, String title) {
        Book book = bookRepository.getBook(id);
        if (book == null) {
            throw new BookNotFoundException("book not found");
        }
        book.setTitle(title);
        return bookRepository.updateBook(book);
    }

    @Override
    @ShellMethod(value = "delete book by id", key = {"d", "delete"})
    @Transactional
    public void deleteBook(long id) {
        Book book = bookRepository.getBook(id);
        if (book == null) {
            throw new BookNotFoundException("book not found");
        }
        bookRepository.deleteBook(book);
    }

    @Override
    @ShellMethod(value = "get comments list by bookId", key = {"lc", "list-comments"})
    @Transactional(readOnly = true)
    public FormattedList<BookComment> getAllComments(long bookId) {
        return bookCommentRepository.getByBookId(bookId);
    }

    @Override
    @ShellMethod(value = "get comment by id", key = {"rc", "read-comment"})
    @Transactional(readOnly = true)
    public BookComment getComment(long commentId) {
        return bookCommentRepository.getById(commentId);
    }

    @Override
    @ShellMethod(value = "append comment for book", key = {"cc", "create-comment"})
    @Transactional
    public BookComment appendComment(long bookId, String text) {
        Book book = bookRepository.getBook(bookId);
        BookComment bookComment = new BookComment(text, book);
        return bookCommentRepository.appendComment(bookComment);
    }

    @Override
    @ShellMethod(value = "append comment for book", key = {"dc", "delete-comment"})
    @Transactional
    public void deleteComment(long bookCommentId) {
        BookComment bookComment = bookCommentRepository.getById(bookCommentId);
        bookCommentRepository.deleteComment(bookComment);
    }

    @Override
    @ShellMethod(value = "update comment for book", key = {"uc", "update-comment"})
    @Transactional
    public BookComment updateComment(long bookCommentId, String text) {
        BookComment bookComment = bookCommentRepository.getById(bookCommentId);
        bookComment.setText(text);
        return bookCommentRepository.updateComment(bookComment);
    }
}
