package com.banana.proyectostareas.exception;

public class ProyectoNotfoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProyectoNotfoundException(String message) {
        super(message);
    }
}
