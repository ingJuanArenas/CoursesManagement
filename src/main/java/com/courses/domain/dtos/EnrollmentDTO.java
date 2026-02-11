package com.courses.domain.dtos;


import com.courses.persistence.model.EnrollmentStatus;

import jakarta.validation.constraints.NotNull;

public record EnrollmentDTO(
    Long id,

    @NotNull(message = "Course ID is required")
    Long courseId,

    @NotNull(message = "Student ID is required")
    Long studentId,

    EnrollmentStatus status
) {} 