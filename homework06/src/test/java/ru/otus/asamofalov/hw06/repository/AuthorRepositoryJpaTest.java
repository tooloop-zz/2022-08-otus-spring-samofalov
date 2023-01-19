package ru.otus.asamofalov.hw06.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.asamofalov.hw06.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("AuthorRepositoryJpa must...")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    AuthorRepository authorRepository;

    @DisplayName("..return appended or stored author")
    @Test
    void shouldAppendNewGenres() {
        Author author = new Author("NewAuthor");
        Author appended = authorRepository.append(author);
        Author found = testEntityManager.find(Author.class, appended.getId());
        assertThat(found).isNotNull().usingRecursiveComparison().isEqualTo(appended);
    }

    @DisplayName("..return all items from storage")
    @Test
    void shouldCheckGetAllNotEmpty() {
        assertNotEquals(0, authorRepository.getAll().size());
    }

    @DisplayName("..return author from storage")
    @Test
    void shouldReturnAuthorFromStorage() {
        var author = authorRepository.getByName("Plagiarist");
        assertAll(
                () -> assertNotNull(author),
                () -> assertNotEquals(0, author.getId()),
                () -> assertEquals("Plagiarist", author.getName())
        );
    }
}