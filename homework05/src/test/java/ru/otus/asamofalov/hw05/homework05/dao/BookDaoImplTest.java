package ru.otus.asamofalov.hw05.homework05.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.asamofalov.hw05.homework05.domain.Book;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {

    @Autowired
    BookDaoImpl bookDao;

    @Test
    void getAll() {
        var books = bookDao.getAll();
        assertEquals(3, books.size());
    }
}