package com.courses.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.repository.EnrollmentsRepositoryInterface;
import com.courses.persistence.crud.EnrollmentsCRUD;
import com.courses.persistence.mapper.EnrollmentsMapper;
import com.courses.persistence.model.Enrollment;
import com.courses.persistence.model.EnrollmentStatus;
import com.courses.persistence.projections.CoursesSummary;
import com.courses.persistence.projections.EnrollmentsSummary;
import com.courses.persistence.projections.StudentsSummary;

@Repository
public class EnrollmentsRepositoryImpl implements EnrollmentsRepositoryInterface {


    private final EnrollmentsCRUD enrollmentsCRUD;
    private final EnrollmentsMapper enrollmentsMapper;

    

    public EnrollmentsRepositoryImpl(EnrollmentsCRUD enrollmentsCRUD,EnrollmentsMapper enrollmentsMapper) {
        this.enrollmentsCRUD = enrollmentsCRUD;
        this.enrollmentsMapper = enrollmentsMapper;
    }

    @Override
    public Page<EnrollmentDTO> getAll(Pageable pageable) {
        return enrollmentsCRUD.findAll(pageable).map(enrollmentsMapper::toDto);
    }

    @Override
    public EnrollmentsSummary getById(Long id) {
        return enrollmentsCRUD.findEnrollmentById(id).orElseThrow(
            () -> new NotFoundException("Enrollment not found with id: " + id)
        );
    }


    public Page<CoursesSummary> getCoursesByStudentId(Long id, Pageable pageable) {
        return enrollmentsCRUD.findCoursesByStudentId(id, pageable);
    }

    public Page<StudentsSummary> getStudentsByCourseId(Long id, Pageable pageable){
        return enrollmentsCRUD.findStudentsByCourseId(id, pageable);
    }

    @Override
    public Page<EnrollmentDTO> getAllByStatus(EnrollmentStatus status, Pageable pageable) {
        return enrollmentsCRUD.findByStatus(status, pageable).map(enrollmentsMapper::toDto);
    }


    @Override
    public Page<EnrollmentDTO> getAllByCourseId(Long courseId, Pageable pageable) {
        return enrollmentsCRUD.findByCourseId(courseId, pageable).map(enrollmentsMapper::toDto);
    }


    @Override
    public Page<EnrollmentDTO> getAllByStudentId(Long studentId, Pageable pageable) {
        return enrollmentsCRUD.findByStudentId(studentId, pageable).map(enrollmentsMapper::toDto);
    }
    

    @Override
    public EnrollmentDTO save(EnrollmentDTO enrollment) {
         Enrollment enrollmentEntity = enrollmentsMapper.toEntity(enrollment);
        enrollmentEntity.setStatus(EnrollmentStatus.ENROLLED);
        return enrollmentsMapper.toDto(enrollmentsCRUD.save(enrollmentEntity));
        
    }

    @Override
    public EnrollmentDTO update(Enrollment enrollment) { 
        return enrollmentsMapper.toDto(enrollmentsCRUD.save(enrollment));
    }

    @Override
    public void delete(Enrollment enrollment) {
       enrollmentsCRUD.delete(enrollment);
        
    }


    
}
