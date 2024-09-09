package com.ttma.classicClothes.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ErrorResponse handleException(Exception e, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=",""));
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
