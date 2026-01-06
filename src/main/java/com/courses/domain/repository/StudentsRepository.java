package com.courses.domain.repository;

import java.util.List;

import com.courses.domain.dtos.StudentDTO;

public interface StudentsRepository {
    
    List<StudentDTO> getAll();
    StudentDTO getById(Long id);
    StudentDTO getByName(String name);
    StudentDTO save (StudentDTO studentDTO);
    StudentDTO update(Long id, StudentDTO studentDTO);
    void delete(Long id);

}
