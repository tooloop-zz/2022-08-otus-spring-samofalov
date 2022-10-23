package ru.otus.asamofalov.hw05.homework05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw05.homework05.domain.Author;
import ru.otus.asamofalov.hw05.homework05.helper.OverridedList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorMapper authorMapper = new AuthorMapper();

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long getIdByName(String name) {
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select id from authors where name = :name",
                    Map.of("name", name),
                    Long.class
            );
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long insertAuthor(Author author) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", author.getName());
        namedParameterJdbcOperations.update(
                "insert into authors (name) values (:name)",
                mapSqlParameterSource,
                generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }

    @Override
    public OverridedList<Author> getAll() {
        return new OverridedList<>(namedParameterJdbcOperations.query(
                "select name from authors",
                authorMapper));
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Author author = new Author(resultSet.getString("name"));
            return author;
        }
    }
}
