package com.courses.domain.dtos;

public record EnrollmentDTO(
    String status,
    Long courseId,
    Long studentId
) {} 