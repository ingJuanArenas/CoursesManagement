package com.courses.persistence.crud;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.courses.domain.projections.CoursesSummary;
import com.courses.domain.projections.StudentsSummary;
import com.courses.persistence.model.Enrollment;

public interface EnrollmentsCRUD extends ListCrudRepository<Enrollment,Long> {



    @Query("SELECT DISTINCT c FROM Course c JOIN FETCH Enrollment e ON c.id = e.courseId WHERE e.studentId = :id")
    List<CoursesSummary> findCoursesByStudentId(Long id);

    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH Enrollment e ON s.id= e.studentId WHERE e.courseId = :id")
    List<StudentsSummary> findStudentsByCourseId(Long id);


    List<Enrollment> findAllByEnrollmentDate(LocalDate date);
}
