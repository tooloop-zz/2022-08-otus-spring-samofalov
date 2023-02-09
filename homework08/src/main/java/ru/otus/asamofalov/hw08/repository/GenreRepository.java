package ru.otus.asamofalov.hw08.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw08.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, String> {
    Genre findByName(String name);
}
