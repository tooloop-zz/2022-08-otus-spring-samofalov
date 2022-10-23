package ru.otus.asamofalov.hw05.homework05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.asamofalov.hw05.homework05.dao.AuthorDao;
import ru.otus.asamofalov.hw05.homework05.dao.BookDao;
import ru.otus.asamofalov.hw05.homework05.dao.GenreDao;
import ru.otus.asamofalov.hw05.homework05.domain.Author;
import ru.otus.asamofalov.hw05.homework05.domain.Book;
import ru.otus.asamofalov.hw05.homework05.domain.Genre;
import ru.otus.asamofalov.hw05.homework05.helper.OverridedList;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest("ru.otus.asamofalov.hw05.homework05.dao")
@DisplayName("BookServiceImpl class")
class BookServiceImplTest {

    @MockBean
    BookDao bookDao;

    @MockBean
    AuthorDao authorDao;

    @MockBean
    GenreDao genreDao;

    @Test
    @DisplayName("check for book exists in collection")
    void shouldReturnCollectionWithMyBook() {
        Book book = new Book("title", new Author("author"), new Genre("genre"));
        when(bookDao.getAll()).thenReturn(new OverridedList<>(Arrays.asList(book)));
        BookService bookService = new BookServiceImpl(bookDao, authorDao, genreDao);
        assertThat(bookService.getAll()).contains(book);
    }

    @Test
    @DisplayName("check for book appended")
    void shouldReturnCorrectAppendAnswer() {
        BookService bookService = new BookServiceImpl(bookDao, authorDao, genreDao);
        assertEquals("book appended", bookService.appendBook("", "", ""));
    }

    @Test
    @DisplayName("check for my book found")
    void shouldReturnMyBook() {
        Book book = new Book("title", new Author("author"), new Genre("genre"));
        when(bookDao.getByTitleAndAuthorName(any(), any())).thenReturn(book);
        BookService bookService = new BookServiceImpl(bookDao, authorDao, genreDao);
        assertEquals("title", bookService.getBook("title", "author").getTitle());
    }

    @Test
    @DisplayName("check for book updated")
    void shouldReturnCorrectUpdateAnswer() {
        BookService bookService = new BookServiceImpl(bookDao, authorDao, genreDao);
        assertEquals("book updated", bookService.updateBook("", "", ""));
    }

    @Test
    @DisplayName("check for book deleted")
    void shouldReturnCorrectDeleteAnswer() {
        BookService bookService = new BookServiceImpl(bookDao, authorDao, genreDao);
        assertEquals("book deleted", bookService.deleteBook("", ""));
    }

    @Test
    @DisplayName("check for author exists in collection")
    void shouldReturnCollectionWithMyAuthor() {
        Author author = new Author("author");
        when(authorDao.getAll()).thenReturn(new OverridedList<>(List.of(author)));
        BookService bookService = new BookServiceImpl(bookDao, authorDao, genreDao);
        assertThat(bookService.getAllAuthors()).contains(author);
    }

    @Test
    @DisplayName("check for genre exists in collection")
    void shouldReturnCollectionWithMyGenre() {
        Genre genre = new Genre("genre");
        when(genreDao.getAll()).thenReturn(new OverridedList<>(List.of(genre)));
        BookService bookService = new BookServiceImpl(bookDao, authorDao, genreDao);
        assertThat(bookService.getAllGenres()).contains(genre);
    }
}