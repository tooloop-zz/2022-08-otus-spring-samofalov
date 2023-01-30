package ru.otus.asamofalov.hw06.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw06.domain.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class BookRepositoryJpa implements BookRepository {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @PersistenceContext
    EntityManager entityManager;

    public BookRepositoryJpa(AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> getAll() {
        return entityManager.createQuery(
                "select b from Book b join fetch b.author join fetch b.genre", Book.class).getResultList();
    }

    @Override
    public Book getById(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public Book getByIdWithComments(long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("book-comments-entity-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        return entityManager.find(Book.class, id, properties);
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }

    @Override
    public Book append(Book book) {
        checkForAuthor(book);
        checkForGenre(book);
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        checkForAuthor(book);
        checkForGenre(book);
        return entityManager.merge(book);
    }

    private void checkForAuthor(Book book) {
        if (book.getAuthor().getId() == 0)
            book.setAuthor(authorRepository.append(book.getAuthor()));
    }

    private void checkForGenre(Book book) {
        if (book.getGenre().getId() == 0)
            book.setGenre(genreRepository.append(book.getGenre()));
    }
}
