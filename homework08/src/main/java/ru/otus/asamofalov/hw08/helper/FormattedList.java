package ru.otus.asamofalov.hw08.helper;

import java.util.ArrayList;

public class FormattedList<T> extends ArrayList<T> {

    public FormattedList(Iterable<T> iterable) {
        iterable.forEach(super::add);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.forEach((s) -> {
            stringBuilder.append(stringBuilder.length() > 0 ? "\n" : "");
            stringBuilder.append(s.toString());
        });
        return stringBuilder.toString();
    }
}