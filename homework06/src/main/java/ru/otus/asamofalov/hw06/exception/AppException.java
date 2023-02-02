package ru.otus.asamofalov.hw06.exception;

public class AppException extends RuntimeException {

    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }
}
