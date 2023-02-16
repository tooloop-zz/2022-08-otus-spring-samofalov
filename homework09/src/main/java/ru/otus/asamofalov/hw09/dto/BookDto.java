package ru.otus.asamofalov.hw09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.asamofalov.hw09.domain.Author;
import ru.otus.asamofalov.hw09.domain.Book;
import ru.otus.asamofalov.hw09.domain.BookComment;
import ru.otus.asamofalov.hw09.domain.Genre;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private long id;

    @NotBlank(message = "Нужно ввести название книги")
    private String title;

    @NotBlank(message = "Нужно указать автора")
    private String author;

    @NotBlank(message = "Нужно указать жанр")
    private String genre;

    private List<BookComment> comments;

    public static Book toDomainObject(BookDto bookDto) {
        return new Book(bookDto.id, bookDto.title, new Author(bookDto.author), new Genre(bookDto.genre));
    }

    public static BookDto fromDomainObject(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName(), book.getComments());
    }
}
