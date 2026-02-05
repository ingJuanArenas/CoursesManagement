package com.courses.domain.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.persistence.model.EnrollmentStatus;

public interface EnrollmentsRepositoryInterface {

    Page<EnrollmentDTO> getAll(int page, int size);
    Page<EnrollmentDTO> getAllByDate(LocalDate date, int page, int size);
    Page<EnrollmentDTO> getAllByStatus(EnrollmentStatus status, int page, int size);
    Page<EnrollmentDTO> getAllByCourseId(Long courseId, int page, int size);
    Page<EnrollmentDTO> getAllByStudentId(Long studentId, int page, int size);
    EnrollmentDTO getById(Long id);
    EnrollmentDTO save(EnrollmentDTO enrollmentDTO);
    EnrollmentDTO update(Long id, EnrollmentDTO enrollmentDTO);
    void delete(Long id);
} 