package com.courses.web.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.courses.domain.exceptions.AlreadyExistsException;

@RestControllerAdvice
public class ExceptionsHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException e) {
        Error error = new Error("Not Found", e.toString());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Error> handleAlreadyExistsException(AlreadyExistsException e) {
        Error error = new Error("Already Exists", e.toString());
        return ResponseEntity.status(409).body(error);
    }



    @ExceptionHandler(Exception.class)
    public Error handleException(Exception e) {
        return new Error("Exception", e.getMessage());
    }
}
