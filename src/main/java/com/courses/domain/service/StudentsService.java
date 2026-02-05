package com.courses.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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

    public Page<StudentsSummary> getAll(int page, int size) {
        return studentRepository.getAll(page, size);
    }

    public StudentDTO getById(Long id) {
        return studentRepository.getById(id);
    }

    public Page<StudentsSummary> getByName(String name, int page, int size) {
        return studentRepository.getByName(name, page, size);
    }


    public Page<CoursesSummary> getCoursesByStudentId(Long id, int page, int size) {
        return studentRepository.getCoursesByStudentId(id, page, size);
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


