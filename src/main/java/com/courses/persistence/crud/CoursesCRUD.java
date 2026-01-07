package com.courses.persistence.crud;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.courses.persistence.model.Course;

public interface CoursesCRUD extends ListCrudRepository<Course, Long>{
    List<Course> findAllByNameContainingIgnoreCase(String name);

}
