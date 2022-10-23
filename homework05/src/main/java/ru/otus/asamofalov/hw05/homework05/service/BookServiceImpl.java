package ru.otus.asamofalov.hw05.homework05.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.asamofalov.hw05.homework05.dao.AuthorDao;
import ru.otus.asamofalov.hw05.homework05.dao.BookDao;
import ru.otus.asamofalov.hw05.homework05.dao.GenreDao;
import ru.otus.asamofalov.hw05.homework05.domain.Author;
import ru.otus.asamofalov.hw05.homework05.domain.Book;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;
import ru.otus.asamofalov.hw05.homework05.helper.OverridedList;

import java.util.List;

@ShellComponent
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    @ShellMethod(key = {"l", "list-books"}, value = "get books list")
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    @ShellMethod(value = "append new book", key = {"c", "create"})
    public String appendBook(String title, String author, String genre) {
        bookDao.appendBook(new Book(title, new Author(author), new Genre(genre)));
        return "book appended";
    }

    @Override
    @ShellMethod(value = "get book by title and author", key = {"r", "read"})
    public Book getBook(String title, String author) {
        return bookDao.getByTitleAndAuthorName(title, author);
    }

    @Override
    @ShellMethod(value = "update book by title and author", key = {"u", "update"})
    public String updateBook(String title, String author, String genre) {
        bookDao.updateBook(new Book(title, new Author(author), new Genre(genre)));
        return "book updated";
    }

    @Override
    @ShellMethod(value = "delete book by title and author", key = {"d", "delete"})
    public String deleteBook(String title, String author) {
        bookDao.deleteBook(new Book(title, new Author(author), null));
        return "book deleted";
    }

    @Override
    @ShellMethod(value = "get authors list", key = {"la", "list-authors"})
    public OverridedList<Author> getAllAuthors() {
        return authorDao.getAll();
    }

    @Override
    @ShellMethod(value = "get genres list", key = {"lg", "list-genres"})
    public OverridedList<Genre> getAllGenres() {
        return genreDao.getAll();
    }
}
