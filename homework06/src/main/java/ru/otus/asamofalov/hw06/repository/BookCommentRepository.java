package ru.otus.asamofalov.hw06.repository;

import ru.otus.asamofalov.hw06.domain.BookComment;

public interface BookCommentRepository {

    BookComment getById(long id);

    BookComment append(BookComment bookComment);

    BookComment update(BookComment bookComment);

    void delete(BookComment bookComment);
}
