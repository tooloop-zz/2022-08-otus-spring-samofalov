package ru.otus.asamofalov.hw08.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw08.domain.BookComment;

public interface BookCommentRepository extends CrudRepository<BookComment, String> {
}
