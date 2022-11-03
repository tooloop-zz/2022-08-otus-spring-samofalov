package ru.otus.asamofalov.hw06.helper;

import java.util.ArrayList;
import java.util.List;

public class FormattedList<T> extends ArrayList<T> {

    public FormattedList(List<T> list) {
        super.addAll(list);
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
