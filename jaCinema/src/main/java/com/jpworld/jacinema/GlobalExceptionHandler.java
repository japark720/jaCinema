package com.jpworld.jacinema;

import com.jpworld.jacinema.exceptions.EntityNotFountException;
import com.jpworld.jacinema.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFountException.class)
    public ResponseEntity<ErrorResponse> handlerEntityNotFound(EntityNotFountException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getResultCode());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 파라미터가 널이면 400에러 표시를 200으로 처리하고 필수 파라미터 누락이라고 커스텀으로 표시
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String missingParamName = ex.getParameterName();
        ErrorResponse response = new ErrorResponse("Missing required parameter: " + missingParamName, "400");
        return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK로 반환
    }
}
