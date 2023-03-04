package ru.otus.asamofalov.hw10.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.asamofalov.hw10.domain.Author;
import ru.otus.asamofalov.hw10.domain.Book;
import ru.otus.asamofalov.hw10.domain.BookComment;
import ru.otus.asamofalov.hw10.domain.Genre;
import ru.otus.asamofalov.hw10.dto.AuthorDto;
import ru.otus.asamofalov.hw10.dto.BookDto;
import ru.otus.asamofalov.hw10.dto.GenreDto;
import ru.otus.asamofalov.hw10.repository.AuthorRepository;
import ru.otus.asamofalov.hw10.repository.BookRepository;
import ru.otus.asamofalov.hw10.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("BookControllerApi class must..")
@WebMvcTest(BookControllerApi.class)
class BookControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    GenreRepository genreRepository;

    @Test
    @DisplayName("..return all authors")
    void shouldCheckForAuthors() throws Exception {
        var authors = List.of(new Author(1, "author01"), new Author(2, "author02"));
        when(authorRepository.findAll()).thenReturn(authors);
        var expectedResult = authors.stream()
                .map(AuthorDto::fromDomainObject).collect(Collectors.toList());
        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("..append new author")
    void shouldAppendNewAuthor() throws Exception {
        var authorDto = new AuthorDto(0, "author");
        var author = AuthorDto.toDomainObject(authorDto);
        when(authorRepository.save(any())).thenReturn(author);
        var expectedResult = objectMapper.writeValueAsString(AuthorDto.fromDomainObject(author));
        mockMvc.perform(post("/api/authors").contentType(APPLICATION_JSON).content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    @DisplayName("..return all genres")
    void shouldCheckForGenres() throws Exception {
        var genres = List.of(new Genre(1, "genre01"), new Genre(2, "genre02"));
        when(genreRepository.findAll()).thenReturn(genres);
        var expectedResult = genres.stream()
                .map(GenreDto::fromDomainObject).collect(Collectors.toList());
        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("..append new genre")
    void shouldAppendNewGenre() throws Exception {
        var genreDto = new GenreDto(0, "genre");
        var genre = GenreDto.toDomainObject(genreDto);
        when(genreRepository.save(any())).thenReturn(genre);
        var expectedResult = objectMapper.writeValueAsString(GenreDto.fromDomainObject(genre));
        mockMvc.perform(post("/api/genres").contentType(APPLICATION_JSON).content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    @DisplayName("..return all book")
    void shouldCheckForBooks() throws Exception {
        var author = new Author(1, "author");
        var genre = new Genre(1, "genre");
        var books = List.of(
                new Book(1, "book01", author, genre),
                new Book(2, "book02", author, genre)
        );
        when(bookRepository.findAll()).thenReturn(books);
        var expectedResult = books.stream()
                .map(BookDto::fromDomainObject).collect(Collectors.toList());
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("..return book with comments")
    void shouldCheckForBook() throws Exception {
        var author = new Author(1, "author");
        var genre = new Genre(1, "genre");
        var book = new Book(2, "book", author, genre);
        book.getComments().add(new BookComment(1, "comment01"));
        book.getComments().add(new BookComment(1, "comment02"));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        var expectedResult = BookDto.fromDomainObjectWithComments(book);
        mockMvc.perform(get("/api/books/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("..update book")
    void shouldUpdateBook() throws Exception {
        var bookDto = new BookDto(1, "book", "author", "genre", null);
        var book = BookDto.toDomainObject(bookDto);
        when(bookRepository.save(any())).thenReturn(book);
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(authorRepository.findByName(anyString())).thenReturn(book.getAuthor());
        when(genreRepository.findByName(anyString())).thenReturn(book.getGenre());
        var expectedResult = objectMapper.writeValueAsString(BookDto.fromDomainObject(book));
        mockMvc.perform(put("/api/books/1").contentType(APPLICATION_JSON).content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    @DisplayName("..append new book")
    void shouldAppendNewBook() throws Exception {
        var bookDto = new BookDto(0, "book", "author", "genre", new ArrayList<>());
        var book = BookDto.toDomainObject(bookDto);
        when(bookRepository.save(any())).thenReturn(book);
        when(authorRepository.findByName(anyString())).thenReturn(book.getAuthor());
        when(genreRepository.findByName(anyString())).thenReturn(book.getGenre());
        var expectedResult = objectMapper.writeValueAsString(BookDto.fromDomainObjectWithComments(book));
        mockMvc.perform(post("/api/books").contentType(APPLICATION_JSON).content(expectedResult))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    @DisplayName("..delete book")
    void shouldDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());
    }
}