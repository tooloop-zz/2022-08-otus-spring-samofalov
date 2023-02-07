package ru.otus.asamofalov.hw08.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    String title;

    @DocumentReference
    Author author;

    @DocumentReference
    Genre genre;

    @DocumentReference(lazy = true)
    List<BookComment> comments = new ArrayList<>();

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format("book id:%s, title: %s, (%s), (%s)",
                id, title, author.toString(), genre.toString());
    }
}