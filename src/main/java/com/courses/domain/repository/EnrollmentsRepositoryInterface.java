package com.courses.domain.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.persistence.model.EnrollmentStatus;
import com.courses.persistence.projections.EnrollmentsSummary;

public interface EnrollmentsRepositoryInterface {

    Page<EnrollmentDTO> getAll(Pageable pageable);
    Page<EnrollmentDTO> getAllByStatus(EnrollmentStatus status, Pageable pageable);
    Page<EnrollmentDTO> getAllByCourseId(Long courseId, Pageable pageable);
    Page<EnrollmentDTO> getAllByStudentId(Long studentId, Pageable pageable);
    EnrollmentsSummary getById(Long id);
    EnrollmentDTO save(EnrollmentDTO enrollmentDTO);
    EnrollmentDTO update(Long id, EnrollmentDTO enrollmentDTO);
    void delete(Long id);
} 