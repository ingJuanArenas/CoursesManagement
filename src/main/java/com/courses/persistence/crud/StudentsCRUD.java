package com.courses.persistence.crud;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.courses.persistence.model.Student;
import com.courses.persistence.projections.StudentsSummary;

public interface StudentsCRUD extends JpaRepository<Student,Long>{


    @Query(
        value = """
            select s.name as name, s.email as email
            from Student s
            where s.active = true
            
        """,
        countQuery = """
            select count(s)
            from Student s
            where s.active = true
        """
    )
    Page<StudentsSummary> findAllByActiveTrue(Pageable pageable);

        @Query(
        value = """
            select s.name as name, s.email as email
            from Student s
            where s.active = true
            and LOWER(s.name) like LOWER(CONCAT('%', :name, '%'))
        """,
        countQuery = """
            select count(s)
            from Student s
            where s.active = true
        """
    )
    Page<StudentsSummary> findAllByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable pageable);
}
