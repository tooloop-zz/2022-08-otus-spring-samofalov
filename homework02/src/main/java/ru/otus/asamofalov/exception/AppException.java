package ru.otus.asamofalov.exception;

public class AppException extends RuntimeException {

    public AppException() {
        super();
    }

    public AppException(Throwable e) {
        super(e);
    }

    public AppException(String message, Throwable e) {
        super(message, e);
    }
}
