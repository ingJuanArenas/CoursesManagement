package com.courses.web.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.courses.domain.exceptions.AlreadyExistsException;
import com.courses.domain.exceptions.EnrollmentNotAvaliableException;
import com.courses.domain.exceptions.NotFoundException;

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

    @ExceptionHandler(EnrollmentNotAvaliableException.class)
    public ResponseEntity<Error> handleEnrollmentNotAvaliableException(EnrollmentNotAvaliableException e) {
        Error error = new Error("Enrollment Not Avaliable", e.toString());
        return ResponseEntity.status(400).body(error);
    }

     @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Error> handleNoResourceFoundException(NoResourceFoundException ex) {
        Error error = new Error("Not Found", ex.toString());
        return ResponseEntity.status(404).body(error);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Error> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Error error = new Error("Data Integrity Violation", ex.toString());
        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Error error = new Error("Method Argument Type Mismatch", ex.toString());
        return ResponseEntity.status(400).body(error);
    }
  

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        Error error = new Error("Internal Server Error", e.toString());
        return ResponseEntity.status(500).body(error);
    }
}
