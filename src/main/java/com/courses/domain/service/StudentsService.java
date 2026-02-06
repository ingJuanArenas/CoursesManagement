package com.courses.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.courses.domain.dtos.StudentDTO;
import com.courses.persistence.projections.CoursesSummary;
import com.courses.persistence.projections.StudentsSummary;
import com.courses.persistence.repository.StudentsRepositoryImpl;

@Service
public class StudentsService {
    
    private final StudentsRepositoryImpl studentRepository;

    public StudentsService(StudentsRepositoryImpl studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page<StudentsSummary> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.getAll(pageable);
    }

    public StudentDTO getById(Long id) {
        return studentRepository.getById(id);
    }

    public Page<StudentsSummary> getByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.getByName(name,pageable );
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


