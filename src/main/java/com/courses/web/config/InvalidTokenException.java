package com.courses.web.config;

public class InvalidTokenException  extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }

}
