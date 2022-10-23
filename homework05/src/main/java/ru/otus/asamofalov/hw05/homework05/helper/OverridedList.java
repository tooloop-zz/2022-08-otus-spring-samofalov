package ru.otus.asamofalov.hw05.homework05.helper;

import java.util.ArrayList;
import java.util.List;

public class OverridedList<T> extends ArrayList<T> {

    public OverridedList(List<T> list) {
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
