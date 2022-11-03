package ru.otus.asamofalov.hw06.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@NoArgsConstructor
public class Book {

    @Column(name = "title")
    @Getter
    @Setter
    String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @Getter
    @Setter
    Author author;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @Getter
    @Setter
    Genre genre;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

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
