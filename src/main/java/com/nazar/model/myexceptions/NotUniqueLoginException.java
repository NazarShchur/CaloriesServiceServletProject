package com.nazar.model.myexceptions;

public class NotUniqueLoginException extends Exception {
    private String loginData;
    public NotUniqueLoginException() {
    }

    public NotUniqueLoginException(String message) {
        super(message);
    }

    public NotUniqueLoginException(Throwable throwable) {
        super(throwable);
    }

    public NotUniqueLoginException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
