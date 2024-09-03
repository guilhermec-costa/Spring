package com.me.SpringApp.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.me.SpringApp.domain.User.exceptions.DuplicateUserException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> handleCustomException(CustomException err) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(DuplicateUserException.class)
    private ResponseEntity<String> handleDuplicateUserException(DuplicateUserException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
