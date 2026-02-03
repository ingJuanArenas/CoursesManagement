package com.courses.domain.dtos;

import com.courses.persistence.model.EnrollmentStatus;

public record EnrollmentDTO(
    Long id,
    Long courseId,
    Long studentId,
    EnrollmentStatus status
) {} 