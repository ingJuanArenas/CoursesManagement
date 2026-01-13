package com.courses.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.repository.EnrollmentsRepositoryInterface;
import com.courses.persistence.crud.EnrollmentsCRUD;
import com.courses.persistence.mapper.EnrollmentsMapper;
import com.courses.persistence.model.Enrollment;
import com.courses.persistence.model.EnrollmentStatus;

@Repository
public class EnrollmentsRepositoryImpl implements EnrollmentsRepositoryInterface {


    private final EnrollmentsCRUD enrollmentsCRUD;
    private final EnrollmentsMapper enrollmentsMapper;

    

    public EnrollmentsRepositoryImpl(EnrollmentsCRUD enrollmentsCRUD, EnrollmentsMapper enrollmentsMapper) {
        this.enrollmentsCRUD = enrollmentsCRUD;
        this.enrollmentsMapper = enrollmentsMapper;
    }

    @Override
    public List<EnrollmentDTO> getAll() {
        return enrollmentsMapper.toDtos(enrollmentsCRUD.findAll());
    }

    @Override
    public EnrollmentDTO getById(Long id) {
       return enrollmentsMapper.toDto(enrollmentsCRUD.findById(id).orElseThrow(
        () -> new NotFoundException("Enrollment not found with id: " + id)
       ));
    }

    @Override
    public EnrollmentDTO save(EnrollmentDTO enrollmentDTO) {

        Enrollment enrollment = enrollmentsMapper.toEntity(enrollmentDTO);
        enrollment.setEnrollmentDate(LocalDate.now());

        return enrollmentsMapper.toDto(enrollmentsCRUD.save(enrollment));
        
    }

    @Override
    public EnrollmentDTO update(Long id, EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = enrollmentsCRUD.findById(id).orElseThrow(
            () -> new NotFoundException("Enrollment not found with id: " + id)
        );
        enrollmentsMapper.updateEntityFromDto(enrollment, enrollmentDTO);
        return enrollmentsMapper.toDto(enrollmentsCRUD.save(enrollment));
    }

    @Override
    public void delete(Long id) {
        enrollmentsCRUD.deleteById(id);
    }
    
}
