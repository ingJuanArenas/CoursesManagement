package com.courses.domain.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.courses.domain.dtos.CourseDTO;
import com.courses.persistence.projections.CoursesSummary;
import com.courses.persistence.projections.StudentsSummary;
import com.courses.persistence.repository.CoursesRepositoryImpl;

@Service
public class CoursesService {
    

    private final CoursesRepositoryImpl courseRepository;

    public CoursesService(CoursesRepositoryImpl courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<CoursesSummary> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.getAll(pageable);
    }

    public CourseDTO getById(Long id) {
        return courseRepository.getById(id);
    }

    public Page<CoursesSummary> getByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.getByName(name,pageable);
    }


    public Page<StudentsSummary> getStudentsByCourseId(Long id, int page, int size){
        return courseRepository.getStudentsByCourseId(id, page, size);
    }

    public CourseDTO save (CourseDTO courseDTO) {
        return courseRepository.save(courseDTO);
    }

    public CourseDTO update(Long id, CourseDTO courseDTO) {
        return courseRepository.update(id, courseDTO);
    }

    public CourseDTO updateStatus(Long id, boolean active) {
        return courseRepository.updateStatus(id, active);
    }

    public void delete(Long id) {
        courseRepository.delete(id);
    }
}
