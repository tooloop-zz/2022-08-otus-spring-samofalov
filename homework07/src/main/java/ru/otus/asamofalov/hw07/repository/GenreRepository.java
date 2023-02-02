package ru.otus.asamofalov.hw07.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw07.domain.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAll();

    Genre findByName(String name);
}
