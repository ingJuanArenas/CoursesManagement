package com.courses.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.CourseDTO;
import com.courses.domain.repository.CoursesRepository;

@Repository
public class CoursesRepositoryImpl implements CoursesRepository {

    @Override
    public List<CourseDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public CourseDTO getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public CourseDTO update(Long id, CourseDTO courseDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    
    
}
