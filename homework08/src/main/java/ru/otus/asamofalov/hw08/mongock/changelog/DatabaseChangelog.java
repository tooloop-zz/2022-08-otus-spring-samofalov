package ru.otus.asamofalov.hw08.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.asamofalov.hw08.domain.Author;
import ru.otus.asamofalov.hw08.domain.Book;
import ru.otus.asamofalov.hw08.domain.BookComment;
import ru.otus.asamofalov.hw08.domain.Genre;
import ru.otus.asamofalov.hw08.repository.AuthorRepository;
import ru.otus.asamofalov.hw08.repository.BookCommentRepository;
import ru.otus.asamofalov.hw08.repository.BookRepository;
import ru.otus.asamofalov.hw08.repository.GenreRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "asamofalov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "asamofalov", runAlways = true)
    public void insertAuthors(AuthorRepository authorRepository) {
        authorRepository.save(new Author("Arthur Conan Doyle"));
        authorRepository.save(new Author("Plagiarist"));
        authorRepository.save(new Author("Twain"));
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "asamofalov", runAlways = true)
    public void insertGenres(GenreRepository genreRepository) {
        genreRepository.save(new Genre("Fantasy"));
        genreRepository.save(new Genre("Novel"));
        genreRepository.save(new Genre("Detective"));
        genreRepository.save(new Genre("Travelogue"));
    }

    @ChangeSet(order = "004", id = "insertBooksAndComments", author = "asamofalov", runAlways = true)
    public void insertBooks(BookRepository bookRepository, AuthorRepository authorRepository,
                            GenreRepository genreRepository, BookCommentRepository bookCommentRepository) {

        Book book = new Book("The Adventure of the Dancing Men",
                authorRepository.findByName("Arthur Conan Doyle"),
                genreRepository.findByName("Detective")
        );
        BookComment bookComment = new BookComment("great");
        bookCommentRepository.save(bookComment);
        book.getComments().add(bookComment);
        bookComment = new BookComment("very good");
        bookCommentRepository.save(bookComment);
        book.getComments().add(bookComment);
        bookComment = new BookComment("excellent");
        bookCommentRepository.save(bookComment);
        book.getComments().add(bookComment);
        bookComment = new BookComment("gorgeous");
        bookCommentRepository.save(bookComment);
        book.getComments().add(bookComment);
        bookRepository.save(book);

        book = new Book("The Adventure of the Dancing Men",
                authorRepository.findByName("Plagiarist"),
                genreRepository.findByName("Detective")
        );
        bookComment = new BookComment("poor");
        bookCommentRepository.save(bookComment);
        book.getComments().add(bookComment);
        bookComment = new BookComment("very bad");
        bookCommentRepository.save(bookComment);
        book.getComments().add(bookComment);
        bookRepository.save(book);

        book = new Book("Roughing It",
                authorRepository.findByName("Twain"),
                genreRepository.findByName("Travelogue")
        );
        bookRepository.save(book);
    }
}
