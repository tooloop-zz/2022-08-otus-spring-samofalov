package ru.otus.asamofalov.hw05.homework05.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Book {

    String title;
    Author author;
    Genre genre;
}
