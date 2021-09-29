package com.portfolio.main.exception;

public class CSignUpFailException extends RuntimeException {
    public CSignUpFailException(String msg, Throwable t) {
        super(msg, t);
    }

    public CSignUpFailException(String msg) {
        super(msg);
    }

    public CSignUpFailException() {
        super();
    }
}
