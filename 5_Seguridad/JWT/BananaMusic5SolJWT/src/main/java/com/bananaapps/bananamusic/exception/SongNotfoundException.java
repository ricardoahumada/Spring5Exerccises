package com.bananaapps.bananamusic.exception;

public class SongNotfoundException extends GlobalException {
    private static final long serialVersionUID = 1L;

    public SongNotfoundException(String message) {
        super(message);
    }
}
