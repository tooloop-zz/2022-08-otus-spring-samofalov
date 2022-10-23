package ru.otus.asamofalov.hw05.homework05.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {

    String title;
    Author author;
    Genre genre;

    @Override
    public String toString() {
        return String.format("Title: %s, Author:%s, Genre:%s", title, author.getName(), genre.getName());
    }

}
