package com.courses.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.domain.exceptions.AlreadyExistsException;
import com.courses.domain.exceptions.EnrollmentOperationNotAvaliableException;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.persistence.crud.CoursesCRUD;
import com.courses.persistence.crud.EnrollmentsCRUD;
import com.courses.persistence.crud.StudentsCRUD;
import com.courses.persistence.model.Course;
import com.courses.persistence.model.Enrollment;
import com.courses.persistence.model.EnrollmentStatus;
import com.courses.persistence.model.Student;
import com.courses.persistence.projections.EnrollmentsSummary;
import com.courses.persistence.repository.EnrollmentsRepositoryImpl;

@Service
public class EnrollmentsService {
    
    private final EnrollmentsRepositoryImpl enrollmentsRepository;
    private final StudentsCRUD studentsCRUD;
    private final CoursesCRUD coursesCRUD;
    private final EnrollmentsCRUD enrollmentsCRUD;
    
    public EnrollmentsService(EnrollmentsRepositoryImpl enrollmentsRepository, StudentsCRUD studentsCRUD,
            CoursesCRUD coursesCRUD, EnrollmentsCRUD enrollmentsCRUD) {
        this.enrollmentsRepository = enrollmentsRepository;
        this.studentsCRUD = studentsCRUD;
        this.coursesCRUD = coursesCRUD;
        this.enrollmentsCRUD = enrollmentsCRUD;
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
    
    @Transactional
    public EnrollmentDTO save(EnrollmentDTO dto) {

    Course course = coursesCRUD.findById(dto.courseId())
        .orElseThrow(() -> new NotFoundException("Course Not Found"));

    Student student = studentsCRUD.findById(dto.studentId())
        .orElseThrow(() -> new NotFoundException("Student Not Found"));

    if (!course.isActive() || !student.isActive()) {
        throw new EnrollmentOperationNotAvaliableException("Course or Student is not active");
    }

    if (course.getCapacity() <= 0) {
        throw new EnrollmentOperationNotAvaliableException("Course has reached its limit");
    }

    boolean alreadyExists = enrollmentsCRUD
        .existsByCourseIdAndStudentId(dto.courseId(), dto.studentId());

    if (alreadyExists) {
        throw new AlreadyExistsException("Student already enrolled in this course");
    }

    course.setCapacity(course.getCapacity()-1);
   
    return enrollmentsRepository.save(dto);
}


    @Transactional
    public EnrollmentDTO update(Long id, EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = enrollmentsCRUD.findById(id)
                .orElseThrow(() -> new NotFoundException("Enrollment not found with id: " + id));
       
        Course course = this.coursesCRUD.findById(enrollmentDTO.courseId())
                .orElseThrow(()-> new NotFoundException("Course Not Found"));

        if(course.getEnrollments().contains(enrollment)){
                enrollment.setStatus(enrollmentDTO.status());

                    if(enrollmentDTO.status().equals(EnrollmentStatus.CANCELLED)){
                        course.setCapacity(course.getCapacity()+1);
                    }
           
        }else{
            throw new NotFoundException("Student is not enrolled in this course");
        }
       return  enrollmentsRepository.update(enrollment);
    }

    @Transactional
    public void delete(Long id) {
        Enrollment enrollment = enrollmentsCRUD.findById(id)
                .orElseThrow(()-> new NotFoundException("Enrollment not found with id: " + id ));
        if (enrollment.getStatus().equals(EnrollmentStatus.ENROLLED)){
            throw new EnrollmentOperationNotAvaliableException("To delete an enrollment its status must be CANCELLED OR COMPLETE");
        }
        enrollmentsRepository.delete(enrollment);
    }



}
