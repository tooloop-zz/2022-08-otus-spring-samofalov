package ru.otus.asamofalov.hw10.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw10.domain.BookComment;

public interface BookCommentRepository extends CrudRepository<BookComment, Long> {
}
