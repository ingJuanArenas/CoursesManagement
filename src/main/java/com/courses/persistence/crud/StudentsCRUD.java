package com.courses.persistence.crud;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.courses.persistence.model.Student;

public interface StudentsCRUD extends ListCrudRepository<Student,Long>{
    List<Student> findAllByNameContainingIgnoreCase(String name);
}
