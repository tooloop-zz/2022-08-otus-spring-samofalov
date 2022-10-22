package ru.otus.asamofalov.hw05.homework05.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Author {

    private final String name;

    @Override
    public String toString() {
        return String.format("author name: %s", name);
    }
}
