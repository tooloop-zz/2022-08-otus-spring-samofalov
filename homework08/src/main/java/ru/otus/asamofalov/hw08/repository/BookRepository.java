package ru.otus.asamofalov.hw08.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw08.domain.Book;

public interface BookRepository extends CrudRepository<Book, String> {
}
