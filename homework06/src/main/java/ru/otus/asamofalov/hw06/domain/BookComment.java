package ru.otus.asamofalov.hw06.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_comments")
@NoArgsConstructor
public class BookComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private long id;

    @Column(name = "text")
    @Getter
    @Setter
    private String text;

    @Column(name = "book_id")
    @Getter
    @Setter
    private long bookId;

    public BookComment(String text, long bookId) {
        this.text = text;
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return String.format("comment id:%s, text: %s", id, text);
    }
}
