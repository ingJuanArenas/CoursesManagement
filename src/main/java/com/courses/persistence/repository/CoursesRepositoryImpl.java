package com.courses.persistence.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.courses.domain.dtos.CourseDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.repository.RepositoryInterface;
import com.courses.persistence.crud.CoursesCRUD;
import com.courses.persistence.mapper.CoursesMapper;
import com.courses.persistence.model.Course;
import com.courses.persistence.projections.CoursesSummary;
import com.courses.persistence.projections.StudentsSummary;

@Repository
public class CoursesRepositoryImpl implements RepositoryInterface<CourseDTO,CoursesSummary> {

    private final CoursesCRUD coursesCRUD;
    private final CoursesMapper coursesMapper;
    private final EnrollmentsRepositoryImpl enrollmentsRepositoryImpl;

    

    public CoursesRepositoryImpl(CoursesCRUD coursesCRUD, CoursesMapper coursesMapper,
            EnrollmentsRepositoryImpl enrollmentsRepositoryImpl) {
        this.coursesCRUD = coursesCRUD;
        this.coursesMapper = coursesMapper;
        this.enrollmentsRepositoryImpl = enrollmentsRepositoryImpl;
    }

    @Override
    public Page<CoursesSummary> getAll(Pageable pageable) {
        return coursesCRUD.findAllByActiveTrue(pageable);
    }

    @Override
    public CourseDTO getById(Long id) {
       return coursesMapper.toDto(coursesCRUD.findById(id).orElseThrow(
        () -> new NotFoundException("Course not found with id: " + id)
       ));
    }

    @Override
    public Page<CoursesSummary> getByName(String name, Pageable pageable) {
        return coursesCRUD.findAllByNameContainingIgnoreCase(name, pageable);
    }

  
    public Page<StudentsSummary> getStudentsByCourseId(Long id, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentsRepositoryImpl.getStudentsByCourseId(id, pageable);
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        Course course= coursesMapper.toEntity(courseDTO);
        return coursesMapper.toDto(coursesCRUD.save(course));
    }

    @Override
    @Transactional
    public CourseDTO update(Long id, CourseDTO courseDTO) {
       Course course = coursesCRUD.findById(id).orElseThrow(
        () -> new NotFoundException("Course not found with id: " + id)
       );

       coursesMapper.updateEntityFromDto(courseDTO, course);
       return coursesMapper.toDto(coursesCRUD.save(course));

    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!coursesCRUD.existsById(id)) {
            throw new NotFoundException("Course not found with id: " + id);
        }
        //Soft delete
        Course course = coursesMapper.toEntity(getById(id));
        course.setActive(false);
        coursesCRUD.save(course);
    }


    
    
}
