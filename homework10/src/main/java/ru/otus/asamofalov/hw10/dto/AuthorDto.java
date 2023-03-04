package ru.otus.asamofalov.hw10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.asamofalov.hw10.domain.Author;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private long id;

    @NotBlank(message = "Нужно указать имя автора")
    private String name;

    public static Author toDomainObject(AuthorDto authorDto) {
        return new Author(authorDto.id, authorDto.name);
    }

    public static AuthorDto fromDomainObject(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }
}
