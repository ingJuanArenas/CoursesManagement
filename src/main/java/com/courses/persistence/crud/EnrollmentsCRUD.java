package com.courses.persistence.crud;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.courses.persistence.model.Enrollment;

public interface EnrollmentsCRUD extends ListCrudRepository<Enrollment,Long> {

    @Query("SELECT e.courseId FROM Enrollment e WHERE e.studentId = :id")
    List<Long> findCourseIdsByStudentId(Long id);

    @Query("SELECT e.studentId FROM Enrollment e WHERE e.courseId = :id")
    List<Long> findStudentIdsByCourseId(Long id);

    List<Enrollment> findAllByEnrollmentDate(LocalDate date);
}
