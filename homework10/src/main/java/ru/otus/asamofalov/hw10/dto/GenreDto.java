package ru.otus.asamofalov.hw10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.asamofalov.hw10.domain.Genre;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {

    private long id;

    @NotBlank(message = "Нужно указать наименование жанра")
    private String name;

    public static Genre toDomainObject(GenreDto genreDto) {
        return new Genre(genreDto.id, genreDto.name);
    }

    public static GenreDto fromDomainObject(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
