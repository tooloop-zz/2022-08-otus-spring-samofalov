package ru.otus.asamofalov.hw06.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.asamofalov.hw06.domain.Author;
import ru.otus.asamofalov.hw06.domain.Book;
import ru.otus.asamofalov.hw06.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@DisplayName("BookRepositoryJpa must..")
@Import({BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
class BookRepositoryJpaTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("..return all books from storage")
    void shouldCheckBooksCount() {
        var books = bookRepository.getAll();
        assertNotEquals(0, books.size());
    }

    @Test
    @DisplayName("..return my book")
    void shouldReturnMyBook() {
        var book = bookRepository.getBook(1);
        String text = book.toString();
        assertAll(
                () -> assertNotNull(book),
                () -> assertThat(text).contains("The Adventure of the Dancing Men"),
                () -> assertThat(text).contains("Arthur Conan Doyle")
        );
    }

    @Test
    @DisplayName("..check for book appended")
    void shouldAppendNewBook() {
        Author author = authorRepository.appendAuthor(new Author("New Author"));
        Genre genre = genreRepository.appendGenre(new Genre("Fantasy"));
        Book book = new Book("New Title", author, genre);
        Book appended = bookRepository.appendBook(book);
        var found = testEntityManager.find(Book.class, appended.getId());
        assertThat(found).isNotNull().usingRecursiveComparison().isEqualTo(appended);
    }

    @Test
    @DisplayName("..check for book deleted")
    void shouldDeleteBook() {
        var book = bookRepository.getBook(1L);
        bookRepository.deleteBook(book);
        var found = testEntityManager.find(Book.class, 1L);
        assertNull(found);
    }

    @Test
    @DisplayName("..check for book updated")
    void shouldUpdateBook() {
        var book = bookRepository.getBook(1L);
        book.setTitle(book.getTitle() + "_");
        bookRepository.updateBook(book);
        var found = testEntityManager.find(Book.class, 1L);
        assertThat(found).isNotNull().usingRecursiveComparison().isEqualTo(book);
    }
}
