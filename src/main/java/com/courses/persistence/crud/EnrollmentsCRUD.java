package com.courses.persistence.crud;

import org.springframework.data.repository.ListCrudRepository;

import com.courses.persistence.model.Enrollment;

public interface EnrollmentsCRUD extends ListCrudRepository<Enrollment,Long> {
    
}
