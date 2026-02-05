package com.courses.persistence.crud;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.courses.domain.projections.CoursesSummary;
import com.courses.persistence.model.Course;

public interface CoursesPagebale extends ListPagingAndSortingRepository<Course,Long> {
    Page<CoursesSummary> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<CoursesSummary> findAllByActiveTrue(Pageable pageable);
}
