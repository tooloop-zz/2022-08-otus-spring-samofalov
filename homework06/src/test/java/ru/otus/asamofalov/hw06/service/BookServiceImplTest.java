package ru.otus.asamofalov.hw06.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.asamofalov.hw06.domain.Author;
import ru.otus.asamofalov.hw06.domain.Book;
import ru.otus.asamofalov.hw06.domain.BookComment;
import ru.otus.asamofalov.hw06.domain.Genre;
import ru.otus.asamofalov.hw06.helper.FormattedList;
import ru.otus.asamofalov.hw06.repository.AuthorRepository;
import ru.otus.asamofalov.hw06.repository.BookCommentRepository;
import ru.otus.asamofalov.hw06.repository.BookRepository;
import ru.otus.asamofalov.hw06.repository.GenreRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest("ru.otus.asamofalov.hw06.repository")
@DisplayName("BookServiceImpl class must..")
class BookServiceImplTest {

    @MockBean
    BookRepository bookRepository;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    GenreRepository genreRepository;

    @MockBean
    BookCommentRepository bookCommentRepository;

    @Test
    @DisplayName("..check for collection not empty")
    void shouldReturnCollectionWithMyBook() {
        Book book = new Book("title", new Author("author"), new Genre("genre"));
        when(bookRepository.getAll()).thenReturn(new FormattedList<>(Arrays.asList(book)));
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        assertNotEquals(0, bookService.getAll().size());
    }

    @Test
    @DisplayName("..check for book appended")
    void shouldReturnCorrectAppendAnswer() {
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        when(bookRepository.appendBook(any())).thenReturn(new Book("", new Author(""), new Genre("")));
        assertNotNull(bookService.appendBook("", "", ""));
    }

    @Test
    @DisplayName("..check for my book found")
    void shouldReturnMyBook() {
        var book = new Book("title", new Author("author"), new Genre("genre"));
        when(bookRepository.getBook(anyLong())).thenReturn(book);
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        var found = bookService.getBook(1L);
        assertThat(found).isNotNull().usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    @DisplayName("..check for book updated")
    void shouldReturnCorrectUpdateAnswer() {
        var book = new Book("newtitle", new Author("author"), new Genre("genre"));
        when(bookRepository.getBook(anyLong())).thenReturn(book);
        when(bookRepository.updateBook(any())).thenReturn(book);
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        var updated = bookService.updateBook(1L, "newtitle");
        assertThat(updated).isNotNull().usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    @DisplayName("..check for book deleted")
    void shouldReturnCorrectDeleteAnswer() {
        var book = new Book("title", new Author("author"), new Genre("genre"));
        when(bookRepository.getBook(anyLong())).thenReturn(book);
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        bookService.deleteBook(1L);
    }

    @Test
    @DisplayName("..check for author exists in collection")
    void shouldReturnCollectionWithMyAuthor() {
        Author author = new Author("author");
        when(authorRepository.getAll()).thenReturn(new FormattedList<>(List.of(author)));
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        assertThat(bookService.getAllAuthors()).contains(author);
    }

    @Test
    @DisplayName("..check for genre exists in collection")
    void shouldReturnCollectionWithMyGenre() {
        Genre genre = new Genre("genre");
        when(genreRepository.getAll()).thenReturn(new FormattedList<>(List.of(genre)));
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        assertThat(bookService.getAllGenres()).contains(genre);
    }

    @Test
    @DisplayName("..check for comments exists")
    void shouldReturnAllCommentForBook() {
        BookComment bookComment = new BookComment("comment", new Book("title", new Author("author"), new Genre("genre")));
        when(bookCommentRepository.getByBookId(anyLong())).thenReturn(new FormattedList<>(List.of(bookComment)));
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        assertThat(bookService.getAllComments(1L)).contains(bookComment);
    }

    @Test
    @DisplayName("..check for comment")
    void shouldReturnCommentForBook() {
        BookComment bookComment = new BookComment("comment", new Book("title", new Author("author"), new Genre("genre")));
        when(bookCommentRepository.getById(anyLong())).thenReturn(bookComment);
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        assertThat(bookService.getComment(1L)).isNotNull().usingRecursiveComparison().isEqualTo(bookComment);
    }

    @Test
    @DisplayName("..check for comment appended")
    void shouldAppendCommentToBook() {
        BookComment bookComment = new BookComment("comment", new Book("title", new Author("author"), new Genre("genre")));
        when(bookRepository.getBook(anyLong())).thenReturn(new Book("title", new Author("author"), new Genre("genre")));
        when(bookCommentRepository.appendComment(any())).thenReturn(bookComment);
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        assertThat(bookService.appendComment(1L, "comment")).isNotNull().usingRecursiveComparison().isEqualTo(bookComment);
    }

    @Test
    @DisplayName("..check for comment deleted")
    void shouldDeleteCommentForBook() {
        when(bookRepository.getBook(anyLong())).thenReturn(new Book("title", new Author("author"), new Genre("genre")));
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        bookService.deleteComment(1L);
    }

    @Test
    @DisplayName("..check for comment updated")
    void shouldUpdateCommentToBook() {
        BookComment bookComment = new BookComment("comment", new Book("title", new Author("author"), new Genre("genre")));
        when(bookCommentRepository.getById(anyLong())).thenReturn(bookComment);
        when(bookCommentRepository.updateComment(any())).thenReturn(bookComment);
        BookService bookService = new BookServiceImpl(bookRepository, authorRepository, genreRepository, bookCommentRepository);
        assertThat(bookService.updateComment(1L, "comment")).isNotNull().usingRecursiveComparison().isEqualTo(bookComment);
    }

}