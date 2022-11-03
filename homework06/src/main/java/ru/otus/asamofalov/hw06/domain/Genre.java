package ru.otus.asamofalov.hw06.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
@NoArgsConstructor
public class Genre {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Column(name = "name")
    @Getter
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("genre id:%s, name: %s", id, name);
    }
}
