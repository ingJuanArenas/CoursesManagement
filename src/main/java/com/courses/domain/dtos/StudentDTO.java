package com.courses.domain.dtos;



import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student DTO")
public record StudentDTO(
    String name, 
    String email,
    boolean active
) {}
