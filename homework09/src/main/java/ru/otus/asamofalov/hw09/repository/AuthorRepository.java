package ru.otus.asamofalov.hw09.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw09.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByName(String name);
}
