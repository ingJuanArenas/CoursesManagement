package com.courses.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courses.domain.dtos.CourseDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.service.CoursesService;


@RestController
@RequestMapping("/api/courses")
public class CoursesController {
    
    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAll(){
        List<CourseDTO> courses = coursesService.getAll();
        if (courses.isEmpty()) {
            throw new NotFoundException("No contents found");
        }

        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(coursesService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> getByName(@RequestParam String name){
        List<CourseDTO> courses = coursesService.getByName(name);
        if (courses.isEmpty()) {
            throw new NotFoundException("No courses found");
        }
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save( @RequestBody CourseDTO course){
        return ResponseEntity.ok(coursesService.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable Long id, @RequestBody CourseDTO course){
        return ResponseEntity.ok(coursesService.update(id,course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        coursesService.delete(id);
        return ResponseEntity.ok().build();
    }


}
