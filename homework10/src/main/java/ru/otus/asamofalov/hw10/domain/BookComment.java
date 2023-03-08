package ru.otus.asamofalov.hw10.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "book_comments")
@NoArgsConstructor
public class BookComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "book_id")
    private long bookId;

    public BookComment(long bookId, String text) {
        this.bookId = bookId;
        this.text = text;
    }
}