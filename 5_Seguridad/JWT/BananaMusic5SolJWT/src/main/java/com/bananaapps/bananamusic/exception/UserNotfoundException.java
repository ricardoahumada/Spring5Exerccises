package com.bananaapps.bananamusic.exception;

public class UserNotfoundException extends GlobalException {
    private static final long serialVersionUID = 1L;

    public UserNotfoundException(String message) {
        super(message);
    }
}
