package com.courses.persistence.crud;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.courses.persistence.model.Course;
import com.courses.persistence.projections.CoursesSummary;

public interface CoursesCRUD extends JpaRepository<Course, Long>{


        @Query(
        value = """
            select c.name as name, c.description as description
            from Course c
            where c.active = true
            and LOWER(c.name) like LOWER(CONCAT('%', :name, '%'))
        """,
        countQuery = """
            select count(c)
            from Course c
            where c.active = true
        """
    )
    Page<CoursesSummary> findAllByNameContainingIgnoreCase(@Param("name")String name, Pageable pageable);

        @Query(
        value = """
            select c.name as name, c.description as description
            from Course c
            where c.active = true
        """,
        countQuery = """
            select count(c)
            from Course c
            where c.active = true
        """
    )
    Page<CoursesSummary> findAllByActiveTrue(Pageable pageable);

}
