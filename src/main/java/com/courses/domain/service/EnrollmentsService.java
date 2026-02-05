package com.courses.domain.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.persistence.model.EnrollmentStatus;
import com.courses.persistence.repository.EnrollmentsRepositoryImpl;

@Service
public class EnrollmentsService {
    
    private final EnrollmentsRepositoryImpl enrollmentsRepository;

    public EnrollmentsService(EnrollmentsRepositoryImpl enrollmentsRepository) {
        this.enrollmentsRepository = enrollmentsRepository;
    }

    public Page<EnrollmentDTO> getAll(int page, int size) {
        return enrollmentsRepository.getAll(page, size);
    }

    public EnrollmentDTO getById(Long id) {
        return enrollmentsRepository.getById(id);
    }

    public Page<EnrollmentDTO> getAllByDate(LocalDate date, int page, int size){
        return enrollmentsRepository.getAllByDate(date, page, size);
    }

    
    public Page<EnrollmentDTO> getAllByStatus(EnrollmentStatus status, int page, int size) {
        return enrollmentsRepository.getAllByStatus(status, page, size);
    }

    public Page<EnrollmentDTO> getAllByCourseId(Long courseId, int page, int size) {
        return enrollmentsRepository.getAllByCourseId(courseId, page, size);
    }

    public Page<EnrollmentDTO> getAllByStudentId(Long studentId, int page, int size) {
        return enrollmentsRepository.getAllByStudentId(studentId, page, size);
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
