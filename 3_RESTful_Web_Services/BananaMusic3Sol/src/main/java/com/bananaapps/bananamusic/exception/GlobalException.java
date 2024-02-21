package com.bananaapps.bananamusic.exception;

public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GlobalException() {
    }

    public GlobalException(String message) {
        super(message);
    }
}
