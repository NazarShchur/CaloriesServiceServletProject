package com.nazar.model.myexceptions;

public class UnacceptableDataInputException extends Exception {
    public UnacceptableDataInputException() {
    }
    public UnacceptableDataInputException(String message) {
        super(message);
    }

    public UnacceptableDataInputException(Throwable throwable) {
        super(throwable);
    }

    public UnacceptableDataInputException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
