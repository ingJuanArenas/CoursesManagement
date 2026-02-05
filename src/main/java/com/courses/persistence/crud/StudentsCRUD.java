package com.courses.persistence.crud;


import org.springframework.data.repository.ListCrudRepository;

import com.courses.persistence.model.Student;

public interface StudentsCRUD extends ListCrudRepository<Student,Long>{
}
