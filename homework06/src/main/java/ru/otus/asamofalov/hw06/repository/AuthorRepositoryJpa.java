package ru.otus.asamofalov.hw06.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw06.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Author> getAll() {
        return entityManager.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author getByName(String name) {
        return entityManager.createQuery("select a from Author a where a.name = :name", Author.class).
                setParameter("name", name).
                getSingleResult();
    }

    @Override
    public Author append(Author author) {
        try {
            return getByName(author.getName());
        } catch (NoResultException e) {
            entityManager.persist(author);
            return author;
        }
    }
}
