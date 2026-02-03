package com.courses.domain.service;

import java.time.LocalDate;
import java.util.List;

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

    public List<EnrollmentDTO> getAll() {
        return enrollmentsRepository.getAll();
    }

    public EnrollmentDTO getById(Long id) {
        return enrollmentsRepository.getById(id);
    }

    public List<EnrollmentDTO> getAllByDate(LocalDate date){
        return enrollmentsRepository.getAllByDate(date);
    }

    
    public List<EnrollmentDTO> getAllByStatus(EnrollmentStatus status) {
        return enrollmentsRepository.getAllByStatus(status);
    }

    public List<EnrollmentDTO> getAllByCourseId(Long courseId) {
        return enrollmentsRepository.getAllByCourseId(courseId);
    }

    public List<EnrollmentDTO> getAllByStudentId(Long studentId) {
        return enrollmentsRepository.getAllByStudentId(studentId);
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
