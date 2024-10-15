package com.jpworld.jacinema;

import com.jpworld.jacinema.exceptions.EntityNotFountException;
import com.jpworld.jacinema.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFountException.class)
    public ResponseEntity<ErrorResponse> handlerEntityNotFound(EntityNotFountException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getResultCode());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
