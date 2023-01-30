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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@DisplayName("BookRepositoryJpa must..")
@Import({BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
class BookRepositoryJpaTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("..return all books from storage")
    void shouldCheckBooksCount() {
        var books = bookRepository.getAll();
        assertEquals(4, books.size());
    }

    @Test
    @DisplayName("..return my book")
    void shouldReturnMyBook() {
        var book = bookRepository.getById(1);
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
        Book book = new Book("New Title", new Author("New Author"), new Genre("Fantasy"));
        Book appended = bookRepository.append(book);
        var found = testEntityManager.find(Book.class, appended.getId());
        assertThat(found).isNotNull().usingRecursiveComparison().isEqualTo(appended);
    }

    @Test
    @DisplayName("..check for book deleted")
    void shouldDeleteBook() {
        var book = bookRepository.getById(1L);
        bookRepository.delete(book);
        var found = testEntityManager.find(Book.class, 1L);
        assertNull(found);
    }

    @Test
    @DisplayName("..check for book updated")
    void shouldUpdateBook() {
        var book = bookRepository.getById(1L);
        book.setTitle(book.getTitle() + "_");
        bookRepository.update(book);
        var found = testEntityManager.find(Book.class, 1L);
        assertThat(found).isNotNull().usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    @DisplayName("..check for book's comments count")
    void shouldGetComments() {
        assertEquals(5, bookRepository.getByIdWithComments(1L).getComments().size());
    }
}
