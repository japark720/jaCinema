package com.jpworld.jacinema.exceptions;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    private String resultCode;

    public ErrorResponse(String message, String resultCode) {
        this.message = message;
        this.resultCode = resultCode;
    }
}
