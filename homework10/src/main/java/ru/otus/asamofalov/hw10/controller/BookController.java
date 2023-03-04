package ru.otus.asamofalov.hw10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.otus.asamofalov.hw10.dto.AuthorDto;
import ru.otus.asamofalov.hw10.dto.BookDto;
import ru.otus.asamofalov.hw10.dto.GenreDto;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BookController {

    @Value("${api_url}")
    private String apiUrl;
    private final RestOperations restOperations = new RestTemplate();

    @GetMapping("/authors/")
    public String listAuthorsPage() {
        return "authors/list";
    }

    @GetMapping("/authors/create")
    public String createAuthor(Model model) {
        model.addAttribute("author", new AuthorDto());
        return "authors/create";
    }

    @PostMapping("/authors/create")
    public String createAuthor(@Valid @ModelAttribute("author") AuthorDto authorDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("author", authorDto);
            return "/authors/create";
        }
        restOperations.postForLocation(getApiUrl("/api/authors"), authorDto);
        return "redirect:/authors/";
    }

    @GetMapping("/genres/")
    public String listGenresPage() {
        return "genres/list";
    }

    @GetMapping("/genres/create")
    public String createGenre(Model model) {
        model.addAttribute("genre", new GenreDto());
        return "genres/create";
    }

    @PostMapping("/genres/create")
    public String createGenre(@Valid @ModelAttribute("genre") GenreDto genreDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genre", genreDto);
            return "/genres/create";
        }
        restOperations.postForLocation(getApiUrl("/api/genres"), genreDto);
        return "redirect:/genres/";
    }

    @GetMapping("/")
    public String listPage() {
        return "list";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") long id, Model model) {
        model.addAttribute("bookId", id);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto bookDto,
                           BindingResult bindingResult, Model model) {
        restOperations.put(getApiUrl("/api/books/{id}"), bookDto, Map.of("id", bookDto.getId()));
        return "redirect:/";
    }

    @GetMapping("/read")
    public String readBook(@RequestParam("id") long id, Model model) {
        model.addAttribute("bookId", id);
        return "read";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") long id) {
        restOperations.delete(getApiUrl("/api/books/{id}"), Map.of("id", id));
        return "redirect:/";
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("book", new BookDto());
        return "create";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", bookDto);
            return "create";
        }
        restOperations.postForLocation(getApiUrl("/api/books"), bookDto);
        return "redirect:/";
    }

    private String getApiUrl(String path) {
        return apiUrl + path;
    }
}
