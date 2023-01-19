package ru.otus.asamofalov.hw06.repository;

import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw06.domain.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookCommentRepositoryJpa implements BookCommentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public BookComment getById(long id) {
        return entityManager.find(BookComment.class, id);
    }

    @Override
    public BookComment append(BookComment bookComment) {
        entityManager.persist(bookComment);
        return bookComment;
    }

    @Override
    public BookComment update(BookComment bookComment) {
        return entityManager.merge(bookComment);
    }

    @Override
    public void delete(BookComment bookComment) {
        entityManager.remove(bookComment);
    }
}