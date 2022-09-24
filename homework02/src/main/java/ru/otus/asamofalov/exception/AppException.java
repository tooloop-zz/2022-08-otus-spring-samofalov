package ru.otus.asamofalov.exception;

public class AppException extends Exception {

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
