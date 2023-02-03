package ru.otus.asamofalov.hw07.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw07.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByName(String name);
}
