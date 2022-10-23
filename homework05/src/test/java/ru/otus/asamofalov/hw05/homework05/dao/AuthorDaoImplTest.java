package ru.otus.asamofalov.hw05.homework05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.asamofalov.hw05.homework05.domain.Author;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("AuthorDaoImpl class")
@JdbcTest
class AuthorDaoImplTest {

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;


    @DisplayName("check author exists in storage")
    @Test
    void shouldReturnAuthorId() {
        AuthorDao authorDao = new AuthorDaoImpl(namedParameterJdbcOperations);
        assertNotEquals(0, authorDao.getIdByName("Arthur Conan Doyle"));
    }

    @DisplayName("check author appended to storage")
    @Test
    void shouldAppendNewGenres() {
        AuthorDao authorDao = new AuthorDaoImpl(namedParameterJdbcOperations);
        Author author = new Author("writer");
        assertNotEquals(0, authorDao.insertAuthor(author));
    }

    @DisplayName("check authors storage not empty")
    @Test
    void shouldCheckGetAllNotEmpty() {
        AuthorDao authorDao = new AuthorDaoImpl(namedParameterJdbcOperations);
        assertNotEquals(0, authorDao.getAll().size());
    }
}