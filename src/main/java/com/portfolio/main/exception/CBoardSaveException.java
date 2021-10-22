package com.portfolio.main.exception;

public class CBoardSaveException extends RuntimeException {
    public CBoardSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CBoardSaveException(String msg) {
        super(msg);
    }

    public CBoardSaveException() {
        super();
    }
}
