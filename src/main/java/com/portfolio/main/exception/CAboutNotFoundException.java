package com.portfolio.main.exception;

public class CAboutNotFoundException extends RuntimeException {
    public CAboutNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CAboutNotFoundException(String msg) {
        super(msg);
    }

    public CAboutNotFoundException() {
        super();
    }
}
