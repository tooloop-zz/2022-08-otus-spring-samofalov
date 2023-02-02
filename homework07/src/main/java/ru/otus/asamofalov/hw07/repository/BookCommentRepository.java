package ru.otus.asamofalov.hw07.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.asamofalov.hw07.domain.BookComment;

public interface BookCommentRepository extends CrudRepository<BookComment, Long> {

    BookComment findById(long id);
}
