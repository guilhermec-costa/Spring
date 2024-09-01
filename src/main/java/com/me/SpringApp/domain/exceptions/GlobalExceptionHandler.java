package com.me.SpringApp.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(CustomException.class)
    private ResponseEntity handleCustomException(CustomException err) {
        return new ResponseEntity<>("No risks here!", HttpStatus.OK);
    }
}
