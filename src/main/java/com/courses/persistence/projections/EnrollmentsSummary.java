package com.courses.persistence.projections;

import java.time.LocalDateTime;

import com.courses.persistence.model.EnrollmentStatus;

public interface EnrollmentsSummary {
    Long getId();
    String getStudentName();
    String getCourseName();
    EnrollmentStatus getStatus();
}
