package com.courses.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.CourseDTO;
import com.courses.domain.dtos.StudentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.repository.RepositoryInterface;
import com.courses.persistence.crud.StudentsCRUD;
import com.courses.persistence.mapper.StudentsMapper;
import com.courses.persistence.model.Student;

@Repository
public class StudentsRepositoryImpl  implements RepositoryInterface<StudentDTO>{

    private final StudentsCRUD studentsCRUD;
    private final StudentsMapper studentsMapper;
    private final EnrollmentsRepositoryImpl enrollmentsRepositoryImpl;
    
   public StudentsRepositoryImpl(StudentsCRUD studentsCRUD, StudentsMapper studentsMapper, EnrollmentsRepositoryImpl enrollmentsRepositoryImpl) {
        this.studentsCRUD = studentsCRUD;
        this.studentsMapper = studentsMapper;
        this.enrollmentsRepositoryImpl = enrollmentsRepositoryImpl;
    }

    @Override
    public List<StudentDTO> getAll() {
       return studentsMapper.toDtos(studentsCRUD.findAll());
    }

    @Override
    public StudentDTO getById(Long id) {
        return studentsMapper.toDto(studentsCRUD.findById(id).orElseThrow(
        () -> new NotFoundException("Student not found with id: " + id)
        ));
    }

    @Override
    public List<StudentDTO> getByName(String name) {
       return studentsMapper.toDtos(studentsCRUD.findAllByNameContainingIgnoreCase(name));
    }

    @Override
    public List<StudentDTO> getActive() {
       return studentsMapper.toDtos(studentsCRUD.findByActiveTrue());
    }

   public List<CourseDTO> getCoursesByStudentId(Long id){
      return enrollmentsRepositoryImpl.getCoursesByStudentId(id);
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
       Student student = studentsMapper.toEntity(getById(id));
       studentsCRUD.delete(student);
    }

    

    
    
}
