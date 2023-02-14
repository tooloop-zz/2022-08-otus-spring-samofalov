package ru.otus.asamofalov.hw09.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    String title;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    Genre genre;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private List<BookComment> comments = new ArrayList<>();

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(long id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book() {
        this.author = new Author();
        this.genre = new Genre();
    }
}
