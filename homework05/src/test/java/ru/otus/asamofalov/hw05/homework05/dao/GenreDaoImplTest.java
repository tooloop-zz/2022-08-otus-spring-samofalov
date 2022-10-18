package ru.otus.asamofalov.hw05.homework05.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreDaoImplTest {

    @Autowired
    NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Test
    void getIdByName() {
        GenreDao genreDao = new GenreDaoImpl(namedParameterJdbcOperations);
        assertNotEquals(0, genreDao.getIdByName("Detective"));
    }

    @Test
    void insertGenre() {
        GenreDao genreDao = new GenreDaoImpl(namedParameterJdbcOperations);
        Genre genre = new Genre();
        genre.setName("novell");
        long id= genreDao.insertGenre(genre);
        assertNotEquals(0, id);
        System.out.println(id);
        Genre genre2 = new Genre();
        genre2.setName("fantazy");
        long id2= genreDao.insertGenre(genre2);
        System.out.println(id2);
    }
}