package com.courses.domain.dtos;

import com.courses.persistence.model.EnrollmentStatus;

public record EnrollmentDTO(
    Long id,
    EnrollmentStatus status,
    Long courseId,
    Long studentId
) {} 