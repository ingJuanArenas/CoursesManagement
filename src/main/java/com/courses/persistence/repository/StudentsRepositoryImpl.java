package com.courses.persistence.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.StudentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.projections.CoursesSummary;
import com.courses.domain.projections.StudentsSummary;
import com.courses.domain.repository.RepositoryInterface;
import com.courses.persistence.crud.StudentsCRUD;
import com.courses.persistence.crud.StudentsPageable;
import com.courses.persistence.mapper.StudentsMapper;
import com.courses.persistence.model.Student;

@Repository
public class StudentsRepositoryImpl  implements RepositoryInterface<StudentDTO,StudentsSummary>{

    private final StudentsCRUD studentsCRUD;
    private final StudentsPageable studentsPageable;
    private final StudentsMapper studentsMapper;
   private final EnrollmentsRepositoryImpl enrollmentsRepositoryImpl;

   public StudentsRepositoryImpl(StudentsCRUD studentsCRUD, StudentsPageable studentsPageable, StudentsMapper studentsMapper, EnrollmentsRepositoryImpl enrollmentsRepositoryImpl) {
        this.studentsCRUD = studentsCRUD;
        this.studentsPageable = studentsPageable;
        this.studentsMapper = studentsMapper;
        this.enrollmentsRepositoryImpl = enrollmentsRepositoryImpl;
    }

    @Override
    public Page<StudentsSummary> getAll(int page, int size) {
         Pageable pageable = PageRequest.of(page, size);
         return studentsPageable.findAllByActiveTrue(pageable);
      }

    @Override
    public StudentDTO getById(Long id) {
        return studentsMapper.toDto(studentsCRUD.findById(id).orElseThrow(
        () -> new NotFoundException("Student not found with id: " + id)
        ));
    }

    @Override
    public Page<StudentsSummary> getByName(String name, int page, int size) {
      Pageable pageable = PageRequest.of(page, size);
      return studentsPageable.findAllByNameContainingIgnoreCaseAndActiveTrue(name, pageable);
   }


   public Page<CoursesSummary> getCoursesByStudentId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
      return enrollmentsRepositoryImpl.getCoursesByStudentId(id, pageable);
    }

    
    @Override
    public StudentDTO save(StudentDTO studentDTO) {
       Student student = studentsMapper.toEntity(studentDTO);
       return studentsMapper.toDto(studentsCRUD.save(student));
    }

    @Override
    public StudentDTO update(Long id, StudentDTO studentDTO) {
        Student student = studentsCRUD.findById(id).orElseThrow(
        () -> new NotFoundException("Student not found with id: " + id)
        );


        studentsMapper.UpdateEntityFromDto(studentDTO, student);
        return studentsMapper.toDto(studentsCRUD.save(student));
    }

    @Override
    public void delete(Long id) {
         if (!studentsCRUD.existsById(id)) {
            throw new NotFoundException("Student not found with id: " + id);
         }
         //Soft delete
         Student student = studentsMapper.toEntity(getById(id));
         student.setActive(false);
         studentsCRUD.save(student);
    }

    

    
    
}
