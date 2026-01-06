package com.courses.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.StudentDTO;
import com.courses.domain.repository.StudentsRepository;

@Repository
public class StudentsRepositoryImpl  implements StudentsRepository{

    @Override
    public List<StudentDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public StudentDTO getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<StudentDTO> getByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public StudentDTO update(Long id, StudentDTO studentDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    
    
}
