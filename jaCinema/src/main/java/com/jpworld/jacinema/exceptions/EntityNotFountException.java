package com.jpworld.jacinema.exceptions;

public class EntityNotFountException extends RuntimeException {
    private final String resultCode;

    public EntityNotFountException(String message, String code) {
        super(message);
        resultCode = code;
    }

    public String getResultCode() {
        return resultCode;
    }
}
