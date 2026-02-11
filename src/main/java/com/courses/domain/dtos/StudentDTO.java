package com.courses.domain.dtos;




import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Student DTO")
public record StudentDTO(

    @NotBlank(message = "Student name is required")
    String name, 

    @NotBlank(message = "Student email is required")
    String email
) {}
