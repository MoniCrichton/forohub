package com.alura.forohub.exception;

public class TopicoNotFoundException extends RuntimeException {
    public TopicoNotFoundException(String message) {
        super(message);
    }
}
