package ru.otus.asamofalov.hw07.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw07.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByName(String name);
}
