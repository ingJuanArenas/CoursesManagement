package com.courses.domain.dtos;

public record StudentDTO(
    String name, 
    String email,
    Long courseId
) {}
