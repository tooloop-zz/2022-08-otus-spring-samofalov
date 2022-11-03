package ru.otus.asamofalov.hw06.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.asamofalov.hw06.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("GenreRepositoryJpa must..")
@DataJpaTest()
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    GenreRepository genreRepository;

    @DisplayName("..return appended or found genre")
    @Test
    void shouldAppendNewGenre() {
        Genre genre = new Genre("Fantasy");
        Genre appended = genreRepository.appendGenre(genre);
        Genre found = testEntityManager.find(Genre.class, appended.getId());
        assertThat(found).isNotNull().usingRecursiveComparison().isEqualTo(appended);
    }

    @DisplayName("..return all items from storage")
    @Test
    void shouldCheckGetAllNotEmpty() {
        assertNotEquals(0, genreRepository.getAll().size());
    }

    @DisplayName("..return genre from storage")
    @Test
    void shouldReturnGenreFromStorage() {
        var genre = genreRepository.getByName("Fantasy");
        assertAll(
                () -> assertNotNull(genre),
                () -> assertNotEquals(0, genre.getId()),
                () -> assertEquals("Fantasy", genre.getName())
        );
    }
}