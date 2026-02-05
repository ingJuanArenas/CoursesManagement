package com.courses.persistence.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.domain.exceptions.AlreadyExistsException;
import com.courses.domain.exceptions.EnrollmentOperationNotAvaliableException;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.projections.CoursesSummary;
import com.courses.domain.projections.StudentsSummary;
import com.courses.domain.repository.EnrollmentsRepositoryInterface;
import com.courses.persistence.crud.CoursesCRUD;
import com.courses.persistence.crud.EnrollmentsCRUD;
import com.courses.persistence.crud.EnrollmentsPageable;
import com.courses.persistence.crud.StudentsCRUD;
import com.courses.persistence.mapper.EnrollmentsMapper;
import com.courses.persistence.model.Course;
import com.courses.persistence.model.Enrollment;
import com.courses.persistence.model.EnrollmentStatus;

@Repository
public class EnrollmentsRepositoryImpl implements EnrollmentsRepositoryInterface {


    private final EnrollmentsCRUD enrollmentsCRUD;
    private final EnrollmentsPageable enrollmentsPageable;
    private final CoursesCRUD coursesCRUD;
    
    private final StudentsCRUD studentsCRUD;
    private final EnrollmentsMapper enrollmentsMapper;

    

    public EnrollmentsRepositoryImpl(EnrollmentsCRUD enrollmentsCRUD, EnrollmentsPageable enrollmentsPageable,
            CoursesCRUD coursesCRUD, StudentsCRUD studentsCRUD, EnrollmentsMapper enrollmentsMapper) {
        this.enrollmentsCRUD = enrollmentsCRUD;
        this.enrollmentsPageable = enrollmentsPageable;
        this.coursesCRUD = coursesCRUD;
        this.studentsCRUD = studentsCRUD;
        this.enrollmentsMapper = enrollmentsMapper;
    }

    @Override
    public Page<EnrollmentDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsPageable.findAll(pageable).map(enrollmentsMapper::toDto);
    }

    @Override
    public EnrollmentDTO getById(Long id) {
        return enrollmentsMapper.toDto(enrollmentsCRUD.findById(id).orElseThrow(()->
    new NotFoundException("Enrollment not found")));
    }


    public Page<CoursesSummary> getCoursesByStudentId(Long id, Pageable pageable) {
        return enrollmentsPageable.findCoursesByStudentId(id, pageable);
    }

    public Page<StudentsSummary> getStudentsByCourseId(Long id, Pageable pageable){
        return enrollmentsPageable.findStudentsByCourseId(id, pageable);
    }


    
    @Override
    public Page<EnrollmentDTO> getAllByDate(LocalDate date, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsPageable.findAllByEnrollmentDate(date, pageable).map(enrollmentsMapper::toDto);
    }


    @Override
    public Page<EnrollmentDTO> getAllByStatus(EnrollmentStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsPageable.findByStatus(status, pageable).map(enrollmentsMapper::toDto);
    }


    @Override
    public Page<EnrollmentDTO> getAllByCourseId(Long courseId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsPageable.findByCourseId(courseId, pageable).map(enrollmentsMapper::toDto);
    }


    @Override
    public Page<EnrollmentDTO> getAllByStudentId(Long studentId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsPageable.findByStudentId(studentId, pageable).map(enrollmentsMapper::toDto);
    }
    

    @Override
    public EnrollmentDTO save(EnrollmentDTO enrollmentDTO) {

        var course = this.coursesCRUD.findById(enrollmentDTO.courseId()).orElseThrow(()-> new NotFoundException("Course Not Found"));
        var student = this.studentsCRUD.findById(enrollmentDTO.studentId()).orElseThrow(()-> new NotFoundException("Student Not Found"));

        //verify capacity
        if (course.getCapacity() == 0) {
            throw new EnrollmentOperationNotAvaliableException("Course has reached its limit");
        }

        //avoid duplicity
        course.getEnrollments().stream().forEach(e -> {
            if(e.getStudentId() == student.getId()){
                throw new AlreadyExistsException("Student already enrolled in this course");
            }
        });


        // verify active status
        if(course.isActive() == false || student.isActive() == false){
            throw new EnrollmentOperationNotAvaliableException("Course or Student is not active");
        }
        
        Enrollment enrollment = enrollmentsMapper.toEntity(enrollmentDTO);
        enrollment.setStatus(EnrollmentStatus.ENROLLED);
        enrollment.setEnrollmentDate(LocalDate.now());
        course.setCapacity(course.getCapacity()-1);

        return enrollmentsMapper.toDto(enrollmentsCRUD.save(enrollment));
        
    }

    @Override
    public EnrollmentDTO update(Long id, EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = enrollmentsCRUD.findById(id).orElseThrow(
            () -> new NotFoundException("Enrollment not found with id: " + id)
        );
        Course course = this.coursesCRUD.findById(enrollmentDTO.courseId()).orElseThrow(()-> new NotFoundException("Course Not Found"));
        
        if(course.getEnrollments().contains(enrollment)){
           
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus(enrollmentDTO.status());
            if(enrollmentDTO.status().equals(EnrollmentStatus.CANCELLED)){
                course.setCapacity(course.getCapacity()+1);
            }
           
        }else{
            throw new NotFoundException("Student is not enrolled in this course");
        }
        
        return enrollmentsMapper.toDto(enrollmentsCRUD.save(enrollment));
    }

    @Override
    public void delete(Long id) {
       Enrollment enrollment = enrollmentsCRUD.findById(id).orElseThrow(()-> new NotFoundException("Enrollment not found with id: " + id ));
       if (enrollment.getStatus().equals(EnrollmentStatus.ENROLLED)){
        throw new EnrollmentOperationNotAvaliableException("To delete an enrollment its status must be CANCELLED OR COMPLETE");
       }
       enrollmentsCRUD.delete(enrollment);
        
    }


    
}
