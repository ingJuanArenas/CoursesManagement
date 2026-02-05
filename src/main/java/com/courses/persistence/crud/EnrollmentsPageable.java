package com.courses.persistence.crud;

import java.time.LocalDate;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import com.courses.domain.projections.CoursesSummary;
import com.courses.domain.projections.StudentsSummary;
import com.courses.persistence.model.Enrollment;
import com.courses.persistence.model.EnrollmentStatus;

public interface EnrollmentsPageable extends ListPagingAndSortingRepository<Enrollment, Long> {

    @Query("SELECT DISTINCT c FROM Course c JOIN FETCH Enrollment e ON c.id = e.courseId WHERE e.studentId = :id")
    Page<CoursesSummary> findCoursesByStudentId(Long id,Pageable pageable);

    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH Enrollment e ON s.id= e.studentId WHERE e.courseId = :id")
    Page<StudentsSummary> findStudentsByCourseId(Long id, Pageable pageable);

    Page<Enrollment> findAllByEnrollmentDate(LocalDate date, Pageable pageable);

    Page<Enrollment> findByStatus(EnrollmentStatus status, Pageable pageable);

    Page<Enrollment> findByCourseId(Long courseId, Pageable pageable);

    Page<Enrollment> findByStudentId(Long studentId, Pageable pageable);
}
