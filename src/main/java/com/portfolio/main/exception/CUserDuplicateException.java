package com.portfolio.main.exception;

public class CUserDuplicateException extends RuntimeException {
    public CUserDuplicateException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserDuplicateException(String msg) {
        super(msg);
    }

    public CUserDuplicateException() {
        super();
    }
}
