package com.courses.domain.repository;

import java.util.List;

import com.courses.domain.dtos.CourseDTO;

public interface CoursesRepository {
    

     List<CourseDTO> getAll();
    CourseDTO getById(Long id);
    List<CourseDTO> getByName(String name);
    CourseDTO save (CourseDTO courseDTO);
    CourseDTO update(Long id, CourseDTO courseDTO);
    void delete(Long id);


}
