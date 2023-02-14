package ru.otus.asamofalov.hw09.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.asamofalov.hw09.domain.Author;
import ru.otus.asamofalov.hw09.domain.Book;
import ru.otus.asamofalov.hw09.domain.Genre;
import ru.otus.asamofalov.hw09.dto.BookDto;
import ru.otus.asamofalov.hw09.repository.AuthorRepository;
import ru.otus.asamofalov.hw09.repository.BookRepository;
import ru.otus.asamofalov.hw09.repository.GenreRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = books.stream().map(BookDto::fromDomainObject).collect(Collectors.toList());
        model.addAttribute("books", bookDtos);
        return "list";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") long id, Model model) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", BookDto.fromDomainObject(book));
        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto bookDto,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", bookDto);
            return "edit";
        }
        Book book = bookRepository.findById(bookDto.getId()).get();
        book.setTitle(bookDto.getTitle());
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/read")
    public String readBook(@RequestParam("id") long id, Model model) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", BookDto.fromDomainObject(book));
        return "read";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") long id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("book", new BookDto());
        return "create";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute("book") BookDto bookDto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", bookDto);
            return "create";
        }
        Book book = BookDto.toDomainObject(bookDto);
        book.setAuthor(findOrAppendAuthor(bookDto.getAuthor()));
        book.setGenre(findOrAppendGenre(bookDto.getGenre()));
        bookRepository.save(book);
        return "redirect:/";
    }

    private Author findOrAppendAuthor(String authorName) {
        Author author = authorRepository.findByName(authorName);
        if (author == null) author = authorRepository.save(new Author(authorName));
        return author;
    }

    private Genre findOrAppendGenre(String genreName) {
        Genre genre = genreRepository.findByName(genreName);
        if (genre == null) genre = genreRepository.save(new Genre(genreName));
        return genre;
    }
}
