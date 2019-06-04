package com.uday.indexsearch.exception;

import com.uday.indexsearch.rest.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalApiParamException.class)
    public ResponseEntity<ErrorResponse> handleApiException(){

        ErrorResponse errorResponse=new ErrorResponse
                ( "Error from global exception handler",System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,null, HttpStatus.BAD_REQUEST);
    }
}
