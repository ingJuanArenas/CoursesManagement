package com.courses.persistence.crud;


import org.springframework.data.repository.ListCrudRepository;

import com.courses.persistence.model.Course;

public interface CoursesCRUD extends ListCrudRepository<Course, Long>{


}
