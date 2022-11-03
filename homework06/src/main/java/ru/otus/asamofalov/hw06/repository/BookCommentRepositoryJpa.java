package ru.otus.asamofalov.hw06.repository;

import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw06.domain.BookComment;
import ru.otus.asamofalov.hw06.helper.FormattedList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookCommentRepositoryJpa implements BookCommentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public FormattedList<BookComment> getByBookId(long bookId) {
        return new FormattedList<>(entityManager.createQuery("select c from BookComment c where c.book.id = :book_id")
                .setParameter("book_id", bookId)
                .getResultList());
    }

    @Override
    public BookComment getById(long id) {
        return entityManager.find(BookComment.class, id);
    }

    @Override
    public BookComment appendComment(BookComment bookComment) {
        entityManager.persist(bookComment);
        return bookComment;
    }

    @Override
    public BookComment updateComment(BookComment bookComment) {
        return entityManager.merge(bookComment);
    }

    @Override
    public void deleteComment(BookComment bookComment) {
        entityManager.remove(bookComment);
    }
}