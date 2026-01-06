package com.courses.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.courses.domain.dtos.StudentDTO;

@Service
public class StudentsService {
    
    private final StudentRepositoryImpl studentRepository;

    public StudentsService(StudentRepositoryImpl studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAll() {
        return studentRepository.getAll();
    }

    public StudentDTO getById(Long id) {
        return studentRepository.getById(id);
    }

    public List<StudentDTO> getByName(String name) {
        return studentRepository.getByName(name);
    }

    public StudentDTO save (StudentDTO studentDTO) {
        return studentRepository.save(studentDTO);
    }

    public StudentDTO update(Long id, StudentDTO studentDTO) {
        studentRepository.update(id, studentDTO);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}


