package com.courses.web.exceptions;

public record Error(
    String type,
    String message
) {}
