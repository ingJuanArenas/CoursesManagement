package com.courses.domain.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.persistence.model.EnrollmentStatus;
import com.courses.persistence.projections.EnrollmentsSummary;
import com.courses.persistence.repository.EnrollmentsRepositoryImpl;

@Service
public class EnrollmentsService {
    
    private final EnrollmentsRepositoryImpl enrollmentsRepository;

    public EnrollmentsService(EnrollmentsRepositoryImpl enrollmentsRepository) {
        this.enrollmentsRepository = enrollmentsRepository;
    }

    public Page<EnrollmentDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsRepository.getAll(pageable );
    }

    public EnrollmentsSummary getById(Long id) {
        return enrollmentsRepository.getById(id);
    }

    
    public Page<EnrollmentDTO> getAllByStatus(EnrollmentStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsRepository.getAllByStatus(status, pageable);
    }

    public Page<EnrollmentDTO> getAllByCourseId(Long courseId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsRepository.getAllByCourseId(courseId, pageable);
    }

    public Page<EnrollmentDTO> getAllByStudentId(Long studentId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsRepository.getAllByStudentId(studentId, pageable);
    }
    
    public EnrollmentDTO save(EnrollmentDTO enrollmentDTO){
        return enrollmentsRepository.save(enrollmentDTO);
    }

    public EnrollmentDTO update(Long id, EnrollmentDTO enrollmentDTO) {
       return  enrollmentsRepository.update(id, enrollmentDTO);
    }

    public void delete(Long id) {
        enrollmentsRepository.delete(id);
    }



}
