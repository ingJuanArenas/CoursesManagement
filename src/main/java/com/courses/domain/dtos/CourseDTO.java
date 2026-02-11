package com.courses.domain.dtos;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Course DTO")
public record CourseDTO(
     @NotBlank(message = "Course name is required")
     String name,
     
     String description,

     @NotNull(message = "Course capacity is required")
     @Min(value = 10, message = "Course capacity must be at least 10")
     @Max(value = 100, message = "Course capacity must be less than or equal to 100")
     int capacity
) {}
