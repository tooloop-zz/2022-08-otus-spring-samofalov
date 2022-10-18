package ru.otus.asamofalov.hw05.homework05.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw05.homework05.domain.Author;
import ru.otus.asamofalov.hw05.homework05.domain.Book;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final JdbcOperations jdbcOperations;
    private final BookMapper bookMapper = new BookMapper();

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        jdbcOperations = namedParameterJdbcOperations.getJdbcOperations();
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query(
                "select " +
                        "b.title, " +
                        "a.name as author, " +
                        "g.name as genre from books b " +
                        "left join authors a on a.id=b.author_id " +
                        "left join genres g on g.id=b.genre_id",
                bookMapper);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {

            Author author = new Author();
            author.setName(resultSet.getString("author"));
            Genre genre = new Genre();
            genre.setName(resultSet.getString("genre"));

            Book book = new Book();
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(author);
            book.setGenre(genre);

            return book;
        }
    }

}
