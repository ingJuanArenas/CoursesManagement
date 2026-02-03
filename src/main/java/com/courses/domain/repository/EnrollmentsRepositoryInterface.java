package com.courses.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.persistence.model.EnrollmentStatus;

public interface EnrollmentsRepositoryInterface {

    List<EnrollmentDTO> getAll();
    List<EnrollmentDTO> getAllByDate(LocalDate date);
    List<EnrollmentDTO> getAllByStatus(EnrollmentStatus status);
    List<EnrollmentDTO> getAllByCourseId(Long courseId);
    List<EnrollmentDTO> getAllByStudentId(Long studentId);
    EnrollmentDTO getById(Long id);
    EnrollmentDTO save(EnrollmentDTO enrollmentDTO);
    EnrollmentDTO update(Long id, EnrollmentDTO enrollmentDTO);
    void delete(Long id);
} 