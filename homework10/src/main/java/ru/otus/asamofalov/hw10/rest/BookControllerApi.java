package ru.otus.asamofalov.hw10.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.asamofalov.hw10.domain.Book;
import ru.otus.asamofalov.hw10.dto.AuthorDto;
import ru.otus.asamofalov.hw10.dto.BookDto;
import ru.otus.asamofalov.hw10.dto.GenreDto;
import ru.otus.asamofalov.hw10.repository.AuthorRepository;
import ru.otus.asamofalov.hw10.repository.BookRepository;
import ru.otus.asamofalov.hw10.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class BookControllerApi {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public BookControllerApi(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/api/authors")
    public List<AuthorDto> getAllAuthors() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).
                map(AuthorDto::fromDomainObject).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/authors")
    public ResponseEntity<?> handleAuthorForm(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok().body(authorRepository.save(AuthorDto.toDomainObject(authorDto)));
    }

    @GetMapping("/api/genres")
    public List<GenreDto> getAllGenres() {
        return StreamSupport.stream(genreRepository.findAll().spliterator(), false).
                map(GenreDto::fromDomainObject).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/genres")
    public ResponseEntity<?> handleAuthorForm(@RequestBody GenreDto genreDto) {
        return ResponseEntity.ok().body(genreRepository.save(GenreDto.toDomainObject(genreDto)));
    }

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(BookDto::fromDomainObject).collect(Collectors.toList());
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBook(@PathVariable(value = "id") long id) {
        return BookDto.fromDomainObjectWithComments(bookRepository.findById(id).get());
    }

    @PutMapping("/api/books/{id}")
    public BookDto updateBook(@PathVariable(value = "id") long id, @RequestBody BookDto bookDto) {
        Book book = bookRepository.findById(id).get();
        book.setTitle(bookDto.getTitle());
        if (!book.getAuthor().getName().equals(bookDto.getAuthor())) {
            book.setAuthor(authorRepository.findByName(bookDto.getAuthor()));
        }
        if (!book.getGenre().getName().equals(bookDto.getGenre())) {
            book.setGenre(genreRepository.findByName(bookDto.getGenre()));
        }
        bookRepository.save(book);
        return BookDto.fromDomainObject(book);
    }

    @PostMapping(value = "/api/books")
    public ResponseEntity<?> handleForm(@RequestBody BookDto bookDto) {

        Book book = BookDto.toDomainObject(bookDto);
        book.setAuthor(authorRepository.findByName(bookDto.getAuthor()));
        book.setGenre(genreRepository.findByName(bookDto.getGenre()));
        bookRepository.save(book);

        return ResponseEntity.ok().body(bookDto);
    }

    @DeleteMapping(value = "/api/books/{id}")
    public void deleteBook(@PathVariable(value = "id") long id) {
        bookRepository.deleteById(id);
    }
}
