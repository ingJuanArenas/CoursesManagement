package com.courses.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.courses.domain.dtos.EnrollmentDTO;

public interface EnrollmentsRepositoryInterface {

    List<EnrollmentDTO> getAll();
    List<EnrollmentDTO> getAllByDate(LocalDate date);
    EnrollmentDTO getById(Long id);
    EnrollmentDTO save(EnrollmentDTO enrollmentDTO);
    EnrollmentDTO update(Long id, EnrollmentDTO enrollmentDTO);
    void delete(Long id);
} 