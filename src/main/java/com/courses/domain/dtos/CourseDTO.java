package com.courses.domain.dtos;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Course DTO")
public record CourseDTO(
     
     String name,
     String description,
     int capacity,
     boolean active
) {}
