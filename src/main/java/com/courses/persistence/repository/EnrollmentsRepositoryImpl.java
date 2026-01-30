package com.courses.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.CourseDTO;
import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.domain.dtos.StudentDTO;
import com.courses.domain.exceptions.AlreadyExistsException;
import com.courses.domain.exceptions.EnrollmentOperationNotAvaliableException;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.repository.EnrollmentsRepositoryInterface;
import com.courses.persistence.crud.CoursesCRUD;
import com.courses.persistence.crud.EnrollmentsCRUD;
import com.courses.persistence.crud.StudentsCRUD;
import com.courses.persistence.mapper.CoursesMapper;
import com.courses.persistence.mapper.EnrollmentsMapper;
import com.courses.persistence.mapper.StudentsMapper;
import com.courses.persistence.model.Course;
import com.courses.persistence.model.Enrollment;
import com.courses.persistence.model.EnrollmentStatus;
import com.courses.persistence.model.Student;

@Repository
public class EnrollmentsRepositoryImpl implements EnrollmentsRepositoryInterface {


    private final EnrollmentsCRUD enrollmentsCRUD;
    private final CoursesCRUD coursesCRUD;
    private final StudentsCRUD studentsCRUD;
    private final EnrollmentsMapper enrollmentsMapper;
    private final StudentsMapper studentsMapper;
    private final CoursesMapper coursesMapper;

    
    public EnrollmentsRepositoryImpl(EnrollmentsCRUD enrollmentsCRUD, CoursesCRUD coursesCRUD, StudentsCRUD studentsCRUD, EnrollmentsMapper enrollmentsMapper, StudentsMapper studentsMapper, CoursesMapper coursesMapper) {
        this.enrollmentsCRUD = enrollmentsCRUD;
        this.coursesCRUD = coursesCRUD;
        this.studentsCRUD = studentsCRUD;
        this.enrollmentsMapper = enrollmentsMapper;
        this.studentsMapper = studentsMapper;
        this.coursesMapper = coursesMapper;
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


    public List<CourseDTO> getCoursesByStudentId(Long id){
        List<Course> courses = enrollmentsCRUD.findCoursesByStudentId(id);
        return coursesMapper.toDtos(courses);
    }

    public List<StudentDTO> getStudentsByCourseId(Long id){
        List<Student> students = enrollmentsCRUD.findStudentsByCourseId(id);

        return studentsMapper.toDtos(students);
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


    @Override
    public List<EnrollmentDTO> getAllByDate(LocalDate date) {
        return enrollmentsMapper.toDtos(enrollmentsCRUD.findAllByEnrollmentDate(date));
    }
    
}
