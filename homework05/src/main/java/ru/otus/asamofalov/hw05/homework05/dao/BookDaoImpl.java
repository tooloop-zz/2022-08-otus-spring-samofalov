package ru.otus.asamofalov.hw05.homework05.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.asamofalov.hw05.homework05.domain.Author;
import ru.otus.asamofalov.hw05.homework05.domain.Book;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;
import ru.otus.asamofalov.hw05.homework05.exception.DaoException;
import ru.otus.asamofalov.hw05.homework05.helper.OverridedList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final BookMapper bookMapper = new BookMapper();
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations, GenreDao genreDao, AuthorDao authorDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.genreDao = genreDao;
        this.authorDao = authorDao;
    }

    @Override
    public OverridedList<Book> getAll() {
        return new OverridedList<>(
                namedParameterJdbcOperations.query(
                        "select " +
                                "b.title, " +
                                "a.name as author, " +
                                "g.name as genre from books b " +
                                "join authors a on a.id=b.author_id " +
                                "join genres g on g.id=b.genre_id",
                        bookMapper)
        );
    }

    @Override
    public Book getByTitleAndAuthorName(String title, String authorName) {
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select " +
                            "b.title, " +
                            "a.name as author, " +
                            "g.name as genre from books b " +
                            "join authors a on a.id=b.author_id " +
                            "join genres g on g.id=b.genre_id " +
                            "where b.title = :title and a.name = :authorName",
                    Map.of("title", title, "authorName", authorName),
                    bookMapper);
        } catch (Exception e) {
            log.warn("no book found with title:{} and author:{}", title, authorName);
            return null;
        }
    }

    @Override
    public void deleteBook(Book book) {
        var authorId = authorDao.getIdByName(book.getAuthor().getName());
        namedParameterJdbcOperations.update(
                "delete from books where title = :title and author_id = :author_id",
                Map.of(
                        "title", book.getTitle(),
                        "author_id", authorId
                ));
    }

    @Override
    public void appendBook(Book book) {
        var genreId = genreDao.getIdByName(book.getGenre().getName());
        if (genreId == 0) {
            genreId = genreDao.insertGenre(book.getGenre());
        }
        var authorId = authorDao.getIdByName(book.getAuthor().getName());
        if (authorId == 0) {
            authorId = authorDao.insertAuthor(book.getAuthor());
        }
        namedParameterJdbcOperations.update(
                "insert into books (title, author_id, genre_id) values (:title, :author_id, :genre_id)",
                Map.of(
                        "title", book.getTitle(),
                        "author_id", authorId,
                        "genre_id", genreId
                ));
    }

    @Override
    public void updateBook(Book book) {
        var foundedBook = getByTitleAndAuthorName(book.getTitle(), book.getAuthor().getName());
        if (foundedBook == null) {
            throw new DaoException(String.format("book with title: %s and author: %s not found for updating", book.getTitle(), book.getAuthor().getName()));
        }
        var authorId = authorDao.getIdByName(book.getAuthor().getName());
        var genreId = genreDao.getIdByName(book.getGenre().getName());
        if (genreId == 0) {
            genreId = genreDao.insertGenre(book.getGenre());
        }
        namedParameterJdbcOperations.update(
                "update books set genre_id = :genre_id where title = :title and author_id = :author_id",
                Map.of(
                        "title", book.getTitle(),
                        "author_id", authorId,
                        "genre_id", genreId
                ));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book(
                    resultSet.getString("title"),
                    new Author(resultSet.getString("author")),
                    new Genre(resultSet.getString("genre"))
            );
            return book;
        }
    }
}
