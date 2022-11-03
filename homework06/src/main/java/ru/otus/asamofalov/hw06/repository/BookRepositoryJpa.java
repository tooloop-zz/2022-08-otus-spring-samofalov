package ru.otus.asamofalov.hw06.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw06.domain.Book;
import ru.otus.asamofalov.hw06.helper.FormattedList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public FormattedList<Book> getAll() {
        return new FormattedList<>(entityManager.createQuery("select b from Book b", Book.class).getResultList());
    }

    @Override
    public Book getBook(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public void deleteBook(Book book) {
        entityManager.remove(book);
    }

    @Override
    public Book appendBook(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        return entityManager.merge(book);
    }
}
