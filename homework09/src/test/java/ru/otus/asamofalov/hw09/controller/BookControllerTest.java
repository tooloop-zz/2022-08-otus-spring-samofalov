package ru.otus.asamofalov.hw09.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.asamofalov.hw09.domain.Author;
import ru.otus.asamofalov.hw09.domain.Book;
import ru.otus.asamofalov.hw09.domain.Genre;
import ru.otus.asamofalov.hw09.repository.AuthorRepository;
import ru.otus.asamofalov.hw09.repository.BookRepository;
import ru.otus.asamofalov.hw09.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BookController.class)
@DisplayName("BookController class must..")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    GenreRepository genreRepository;

    @Test
    @DisplayName("..return book list")
    void shouldReturnListOfBook() throws Exception {
        Book book = new Book("title", new Author("author"), new Genre("genre"));
        when(bookRepository.findAll()).thenReturn(List.of(book));
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    @DisplayName("..show edit form")
    void shouldShowEditForm() throws Exception {
        Book book = new Book("title", new Author("author"), new Genre("genre"));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        this.mockMvc
                .perform(get("/edit").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    @DisplayName("..show read form")
    void shouldShowReadForm() throws Exception {
        Book book = new Book("title", new Author("author"), new Genre("genre"));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        this.mockMvc
                .perform(get("/read").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("read"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    @DisplayName("..return target page found")
    void shouldDeleteBook() throws Exception {
        this.mockMvc
                .perform(get("/delete").param("id", "1"))
                .andExpect(status().isFound());
    }
}
