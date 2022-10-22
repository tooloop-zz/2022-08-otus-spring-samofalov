package ru.otus.asamofalov.hw05.homework05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.asamofalov.hw05.homework05.domain.Author;
import ru.otus.asamofalov.hw05.homework05.domain.Book;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@DisplayName("BookDaoImpl class")
@Import({BookDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class})
class BookDaoImplTest {

    @Autowired
    BookDaoImpl bookDao;

    @Test
    @DisplayName("check for books count in storage")
    void shouldCheckBooksCount() {
        var books = bookDao.getAll();
        assertEquals(4, books.size());
    }

    @Test
    @DisplayName("check for book title and author exists and right genre")
    void getByNameAndAuthorName() {
        var book = bookDao.getByTitleAndAuthorName(
                "The Adventure of the Dancing Men",
                "Arthur Conan Doyle");
        assertAll(
                () -> assertNotNull(book),
                () -> assertEquals("Detective", book.getGenre().getName())
        );
    }

    @Test
    @DisplayName("check for book append")
    void shouldAppendNewBook() {
        Book book = new Book("New Title", new Author("New Author"), new Genre("Fantasy"));
        bookDao.appendBook(book);
        Book testBook = bookDao.getByTitleAndAuthorName(book.getTitle(), book.getAuthor().getName());
        assertNotNull(testBook);
    }

    @Test
    @DisplayName("check for book delete")
    void shouldDeleteBook() {
        Book newBook = new Book("New Title", new Author("New Author"), new Genre("Fantasy"));
        bookDao.appendBook(newBook);
        Book appendedBook = bookDao.getByTitleAndAuthorName(newBook.getTitle(), newBook.getAuthor().getName());
        bookDao.deleteBook(newBook);
        Book deletedBook = bookDao.getByTitleAndAuthorName(newBook.getTitle(), newBook.getAuthor().getName());
        assertAll(
                () -> assertNotNull(appendedBook),
                () -> assertNull(deletedBook)
        );
    }
}
