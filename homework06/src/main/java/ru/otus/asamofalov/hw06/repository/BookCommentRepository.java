package ru.otus.asamofalov.hw06.repository;

import ru.otus.asamofalov.hw06.domain.BookComment;
import ru.otus.asamofalov.hw06.helper.FormattedList;

public interface BookCommentRepository {
    FormattedList<BookComment> getByBookId(long bookId);

    BookComment getById(long id);

    BookComment appendComment(BookComment bookComment);

    BookComment updateComment(BookComment bookComment);

    void deleteComment(BookComment bookComment);
}
