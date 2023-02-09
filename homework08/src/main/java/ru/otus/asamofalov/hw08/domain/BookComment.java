package ru.otus.asamofalov.hw08.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bookComments")
public class BookComment {

    @Id
    private String id;

    private String text;

    public BookComment(String text) {
        this.text = text;
    }
}