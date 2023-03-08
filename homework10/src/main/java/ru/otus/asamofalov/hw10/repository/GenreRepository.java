package ru.otus.asamofalov.hw10.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw10.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByName(String name);
}
