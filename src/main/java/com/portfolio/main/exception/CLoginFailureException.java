package com.portfolio.main.exception;

public class CLoginFailureException extends RuntimeException {
    public CLoginFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public CLoginFailureException(String msg) {
        super(msg);
    }

    public CLoginFailureException() {
        super();
    }
}
