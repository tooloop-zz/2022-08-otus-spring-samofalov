package ru.otus.asamofalov.hw09.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw09.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByName(String name);
}
