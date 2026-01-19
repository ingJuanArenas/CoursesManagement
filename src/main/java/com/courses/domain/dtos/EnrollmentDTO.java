package com.courses.domain.dtos;

import com.courses.persistence.model.EnrollmentStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Enrollment DTO")
public record EnrollmentDTO(
    Long id,
    EnrollmentStatus status,
    Long courseId,
    Long studentId
) {} 