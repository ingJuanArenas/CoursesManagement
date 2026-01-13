package com.courses.domain.repository;

import java.util.List;

import com.courses.domain.dtos.EnrollmentDTO;

public interface EnrollmentsRepositoryInterface {

    List<EnrollmentDTO> getAll();
    EnrollmentDTO getById(Long id);
    EnrollmentDTO save(EnrollmentDTO enrollmentDTO);
    EnrollmentDTO update(Long id, EnrollmentDTO enrollmentDTO);
    void delete(Long id);
} 