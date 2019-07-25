package com.nazar.model.myexceptions;

public class NoFoodAddedException extends Exception {
    public NoFoodAddedException() {
    }

    public NoFoodAddedException(String message) {
        super(message);
    }

    public NoFoodAddedException(Throwable throwable) {
        super(throwable);
    }

    public NoFoodAddedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
