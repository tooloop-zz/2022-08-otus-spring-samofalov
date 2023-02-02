package ru.otus.asamofalov.hw07.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw07.domain.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();

    Author findByName(String name);
}
