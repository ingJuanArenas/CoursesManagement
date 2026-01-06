package com.courses.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.courses.domain.dtos.CourseDTO;
import com.courses.persistence.repository.CoursesRepositoryImpl;

@Service
public class CoursesService {
    

    private final CoursesRepositoryImpl courseRepository;

    public CoursesService(CoursesRepositoryImpl courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> getAll() {
        return courseRepository.getAll();
    }

    public CourseDTO getById(Long id) {
        return courseRepository.getById(id);
    }

    public List<CourseDTO> getByName(String name) {
        return courseRepository.getByName(name);
    }

    public CourseDTO save (CourseDTO courseDTO) {
        return courseRepository.save(courseDTO);
    }

    public CourseDTO update(Long id, CourseDTO courseDTO) {
        courseRepository.update(id, courseDTO);
    }

    public void delete(Long id) {
        courseRepository.delete(id);
    }
}
