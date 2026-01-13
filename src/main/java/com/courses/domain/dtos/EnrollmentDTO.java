package com.courses.domain.dtos;

import com.courses.persistence.model.EnrollmentStatus;

public record EnrollmentDTO(
    EnrollmentStatus status,
    Long courseId,
    Long studentId
) {} 