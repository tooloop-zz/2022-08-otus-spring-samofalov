package ru.otus.asamofalov.hw05.homework05.dao;

import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;

import java.util.Map;

public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long getIdByName(String name) {
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select id from genres where name = :name",
                    Map.of("name", name),
                    Long.class
            );
        } catch (IncorrectResultSetColumnCountException e) {
            return 0;
        }
    }

    @Override
    public long insertGenre(Genre genre) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", genre.getName());
        namedParameterJdbcOperations.update(
                "insert into genres (name) values (:name)",
                mapSqlParameterSource,
                generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }
}
