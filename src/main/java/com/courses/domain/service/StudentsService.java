package com.courses.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.courses.domain.dtos.CourseDTO;
import com.courses.domain.dtos.StudentDTO;
import com.courses.domain.projections.CoursesSummary;
import com.courses.domain.projections.StudentsSummary;
import com.courses.persistence.repository.StudentsRepositoryImpl;

@Service
public class StudentsService {
    
    private final StudentsRepositoryImpl studentRepository;

    public StudentsService(StudentsRepositoryImpl studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentsSummary> getAll() {
        return studentRepository.getAll();
    }

    public StudentDTO getById(Long id) {
        return studentRepository.getById(id);
    }

    public List<StudentDTO> getByName(String name) {
        return studentRepository.getByName(name);
    }


    public List<CoursesSummary> getCoursesByStudentId(Long id){
        return studentRepository.getCoursesByStudentId(id);
    }

    public StudentDTO save (StudentDTO studentDTO) {
        return studentRepository.save(studentDTO);
    }

    public StudentDTO update(Long id, StudentDTO studentDTO) {
       return  studentRepository.update(id, studentDTO);
    }

    public void delete(Long id) {
        studentRepository.delete(id);
    }
}


