package ru.otus.asamofalov.hw06.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw06.domain.Genre;
import ru.otus.asamofalov.hw06.helper.FormattedList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Slf4j
@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public FormattedList<Genre> getAll() {
        return new FormattedList<>(entityManager.createQuery("select g from Genre g", Genre.class).getResultList());
    }

    @Override
    public Genre getByName(String name) {
        return entityManager.createQuery("select g from Genre g where g.name = :name", Genre.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Genre append(Genre genre) {
        try {
            return getByName(genre.getName());
        } catch (NoResultException e) {
            entityManager.persist(genre);
            return genre;
        }
    }
}
