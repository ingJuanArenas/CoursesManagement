package com.courses.persistence.crud;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.courses.persistence.model.Enrollment;
import com.courses.persistence.model.EnrollmentStatus;
import com.courses.persistence.projections.CoursesSummary;
import com.courses.persistence.projections.EnrollmentsSummary;
import com.courses.persistence.projections.StudentsSummary;

public interface EnrollmentsCRUD extends JpaRepository<Enrollment,Long> {
     @Query("SELECT DISTINCT c FROM Course c JOIN FETCH Enrollment e ON c.id = e.courseId WHERE e.studentId = :id")
    Page<CoursesSummary> findCoursesByStudentId(Long id,Pageable pageable);

    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH Enrollment e ON s.id= e.studentId WHERE e.courseId = :id")
    Page<StudentsSummary> findStudentsByCourseId(Long id, Pageable pageable);


    Page<Enrollment> findByStatus(EnrollmentStatus status, Pageable pageable);

    Page<Enrollment> findByCourseId(Long courseId, Pageable pageable);

    Page<Enrollment> findByStudentId(Long studentId, Pageable pageable);

    @Query(value = """
        SELECT e.id, s.name AS studentName, c.name AS courseName, e.created_date, e.status
        FROM enrollments e
        JOIN students s ON e.student_id = s.id
        JOIN courses c ON e.course_id = c.id
        WHERE e.id = :id """, nativeQuery = true)
    Optional<EnrollmentsSummary> findEnrollmentById(@Param("id") Long id);

    boolean existsByCourseIdAndStudentId(Long courseId, Long studentId);
  
}
