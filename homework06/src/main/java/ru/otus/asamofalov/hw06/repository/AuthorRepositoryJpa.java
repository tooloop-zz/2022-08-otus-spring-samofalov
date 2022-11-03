package ru.otus.asamofalov.hw06.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw06.domain.Author;
import ru.otus.asamofalov.hw06.helper.FormattedList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public FormattedList<Author> getAll() {
        return new FormattedList<>(entityManager.createQuery("select a from Author a", Author.class).getResultList());
    }

    @Override
    public Author getByName(String name) {
        try {
            return entityManager.createQuery("select a from Author a where a.name = :name", Author.class).
                    setParameter("name", name).
                    getSingleResult();
        } catch (NoResultException e) {
            log.warn("author {} not found", name);
            return null;
        }
    }

    @Override
    public Author appendAuthor(Author author) {
        try {
            entityManager.persist(author);
            return author;
        } catch (Exception e) {
            log.warn("author {} already exists", author.getName());
            return getByName(author.getName());
        }
    }
}
