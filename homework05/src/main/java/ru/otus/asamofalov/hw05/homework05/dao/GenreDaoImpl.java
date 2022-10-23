package ru.otus.asamofalov.hw05.homework05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;
import ru.otus.asamofalov.hw05.homework05.helper.OverridedList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final GenreMapper genreMapper = new GenreMapper();

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
        } catch (Exception e) {
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

    @Override
    public OverridedList<Genre> getAll() {
        return new OverridedList<>(namedParameterJdbcOperations.query(
                "select name from genres",
                genreMapper));
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Genre genre = new Genre(resultSet.getString("name"));
            return genre;
        }
    }
}
