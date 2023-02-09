package ru.otus.asamofalov.hw08.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw08.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, String> {
    Author findByName(String name);
}
