package com.courses.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.courses.domain.dtos.CourseDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.repository.RepositoryInterface;
import com.courses.persistence.crud.CoursesCRUD;
import com.courses.persistence.mapper.CoursesMapper;
import com.courses.persistence.model.Course;

@Repository
public class CoursesRepositoryImpl implements RepositoryInterface<CourseDTO> {

    private final CoursesCRUD coursesCRUD;
    private final CoursesMapper coursesMapper;

    public CoursesRepositoryImpl(CoursesCRUD coursesCRUD, CoursesMapper coursesMapper) {
        this.coursesCRUD = coursesCRUD;
        this.coursesMapper = coursesMapper;

    }

    @Override
    public List<CourseDTO> getAll() {
       return coursesMapper.toDtos(coursesCRUD.findAll());
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

    @Override
    public List<CourseDTO> getActive() {
       return coursesMapper.toDtos(coursesCRUD.findByActiveTrue());
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
        coursesMapper.toEntity(getById(id));     
        coursesCRUD.deleteById(id);
    }


    
    
}
