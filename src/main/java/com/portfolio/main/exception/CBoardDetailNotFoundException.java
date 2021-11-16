package com.portfolio.main.exception;

public class CBoardDetailNotFoundException extends RuntimeException {
	public CBoardDetailNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public CBoardDetailNotFoundException(String msg) {
		super(msg);
	}
	public CBoardDetailNotFoundException() {
		super();
	}
}

