package ru.otus.asamofalov.hw10.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw10.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll();
}
