package com.courses.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.CourseDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.projections.CoursesSummary;
import com.courses.domain.projections.StudentsSummary;
import com.courses.domain.repository.RepositoryInterface;
import com.courses.persistence.crud.CoursesCRUD;
import com.courses.persistence.mapper.CoursesMapper;
import com.courses.persistence.model.Course;

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
    public List<CoursesSummary>getAll() {
        return coursesCRUD.findAllByActiveTrue();
    }

    @Override
    public CourseDTO getById(Long id) {
       return coursesMapper.toDto(coursesCRUD.findById(id).orElseThrow(
        () -> new NotFoundException("Course not found with id: " + id)
       ));
    }

    @Override
    public List<CourseDTO> getByName(String name) {
        return coursesMapper.toDtos(coursesCRUD.findAllByNameContainingIgnoreCase(name));
    }

  
    public List<StudentsSummary> getStudentsByCourseId(Long id){
        return enrollmentsRepositoryImpl.getStudentsByCourseId(id);
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        Course course= coursesMapper.toEntity(courseDTO);
        return coursesMapper.toDto(coursesCRUD.save(course));
    }

    @Override
    public CourseDTO update(Long id, CourseDTO courseDTO) {
       Course course = coursesCRUD.findById(id).orElseThrow(
        () -> new NotFoundException("Course not found with id: " + id)
       );

       coursesMapper.updateEntityFromDto(courseDTO, course);
       return coursesMapper.toDto(coursesCRUD.save(course));

    }

    @Override
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
