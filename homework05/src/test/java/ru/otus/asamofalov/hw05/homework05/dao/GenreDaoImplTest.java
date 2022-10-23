package ru.otus.asamofalov.hw05.homework05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("GenreDaoImpl class")
@JdbcTest
class GenreDaoImplTest {

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;

    @DisplayName("check genre exists in storage")
    @Test
    void shouldReturnGenreId() {
        GenreDao genreDao = new GenreDaoImpl(namedParameterJdbcOperations);
        assertNotEquals(0, genreDao.getIdByName("Detective"));
    }

    @DisplayName("check genres appended to storage")
    @Test
    void shouldAppendNewGenres() {
        GenreDao genreDao = new GenreDaoImpl(namedParameterJdbcOperations);
        Genre genreAction = new Genre("Action");
        Genre genreMystery = new Genre("Mystery");
        assertAll(
                () -> assertNotEquals(0, genreDao.insertGenre(genreAction)),
                () -> assertNotEquals(0, genreDao.insertGenre(genreMystery))
        );
    }

    @DisplayName("check genres storage not empty")
    @Test
    void shouldCheckGetAllNotEmpty() {
        GenreDao genreDao = new GenreDaoImpl(namedParameterJdbcOperations);
        assertNotEquals(0, genreDao.getAll().size());
    }
}