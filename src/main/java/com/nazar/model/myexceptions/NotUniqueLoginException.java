package com.nazar.model.myexceptions;

public class NotUniqueLoginException extends RuntimeException {
    private String loginData;

    public String getLoginData() {
        return loginData;
    }

    public NotUniqueLoginException() {
    }

    public NotUniqueLoginException(String loginData) {
        this.loginData = loginData;
    }

    public NotUniqueLoginException(Throwable throwable) {
        super(throwable);
    }

    public NotUniqueLoginException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
