package com.portfolio.main.exception;

public class CAboutSaveException extends RuntimeException {
    public CAboutSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public CAboutSaveException(String msg) {
        super(msg);
    }

    public CAboutSaveException() {
        super();
    }
}
