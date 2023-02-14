package ru.otus.asamofalov.hw09.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw09.domain.BookComment;

public interface BookCommentRepository extends CrudRepository<BookComment, Long> {
}
