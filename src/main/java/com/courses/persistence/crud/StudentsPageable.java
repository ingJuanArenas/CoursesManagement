package com.courses.persistence.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.courses.domain.projections.StudentsSummary;
import com.courses.persistence.model.Student;

public interface StudentsPageable extends ListPagingAndSortingRepository<Student, Long> {
    
    Page<StudentsSummary> findAllByActiveTrue(Pageable pageable);

    Page<StudentsSummary> findAllByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable pageable);
}
