package ru.otus.asamofalov.hw05.homework05.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw05.homework05.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final JdbcOperations jdbcOperations;
    private final AuthorMapper authorMapper = new AuthorMapper();

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbcOperations = namedParameterJdbcOperations.getJdbcOperations();
    }

    @Override
    public Author getByName(String name) {
        return null;// namedParameterJdbcOperations.queryForObject("select ");
    }

    private static class AuthorMapper implements RowMapper<Author>{

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setName(rs.getString("name"));
            return author;
        }
    }
}
