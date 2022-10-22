package ru.otus.asamofalov.hw05.homework05.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
//@Setter
@RequiredArgsConstructor
public class Genre {

    private final String name;

    @Override
    public String toString() {
        return String.format("genre name: %s", name);
    }
}
