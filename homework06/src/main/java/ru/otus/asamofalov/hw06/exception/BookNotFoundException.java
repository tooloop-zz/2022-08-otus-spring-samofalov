package ru.otus.asamofalov.hw06.exception;

public class BookNotFoundException extends AppException {

    public BookNotFoundException() {
        super("book not found");
    }
}
