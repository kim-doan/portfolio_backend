package com.portfolio.main.exception;

public class CPasswordIncorrectException extends RuntimeException {
    public CPasswordIncorrectException(String msg, Throwable t) {
        super(msg, t);
    }

    public CPasswordIncorrectException(String msg) {
        super(msg);
    }

    public CPasswordIncorrectException() {
        super();
    }
}
