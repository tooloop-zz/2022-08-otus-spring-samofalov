package ru.otus.asamofalov.hw06.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw06.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
        return entityManager.createQuery(
                        "select b from Book b join fetch b.author join fetch b.genre left join fetch b.comments where b.id = :id", Book.class).
                setParameter("id", id).
                getSingleResult();
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }

    private void checkForAuthor(Book book) {
        if (book.getAuthor().getId() == 0)
            book.setAuthor(authorRepository.append(book.getAuthor()));
    }

    private void checkForGenre(Book book) {
        if (book.getGenre().getId() == 0)
            book.setGenre(genreRepository.append(book.getGenre()));
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
}
