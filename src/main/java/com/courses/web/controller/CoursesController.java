package com.courses.web.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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
import com.courses.domain.dtos.StudentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.projections.CoursesSummary;
import com.courses.domain.projections.StudentsSummary;
import com.courses.domain.service.CoursesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/courses")
public class CoursesController {
    
    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }


    @Operation(summary = "Get all active courses", description = "Returns a list of all active courses.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping()
    public ResponseEntity<Page<CoursesSummary>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
         Page<CoursesSummary> courses = coursesService.getAll(page, size);
        if (courses.isEmpty()) {
            throw new NotFoundException("No contents found");
        }

        return ResponseEntity.ok(courses);
    }


    @Operation(summary = "Get course by ID", description = "Returns a course by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(coursesService.getById(id));
    }


    @Operation(summary = "Search courses by name", description = "Returns a list of courses by their name.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<Page<CoursesSummary>> getByName(@RequestParam("name") String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
         Page<CoursesSummary> courses = coursesService.getByName(name, page, size);
        if (courses.isEmpty()) {
            throw new NotFoundException("No courses found");
        }


        return ResponseEntity.ok(courses);
    }



    @Operation(summary = "Get all students enrollment in a course", description = "Returns a list of students enrollment in a course.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping("/{id}/students")
    public ResponseEntity<Page<StudentsSummary>> getStudentsByCourseId(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
         Page<StudentsSummary> students = coursesService.getStudentsByCourseId(id, page, size);
        if (students.isEmpty()) {
            throw new NotFoundException("No students found for this course");
        }
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Save a new course", description = "Saves a new course.")
        @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Created"),
        @ApiResponse(responseCode = "409", description = "Conflict",content = @Content)
    })
    @PostMapping
    public ResponseEntity<CourseDTO> save( @RequestBody CourseDTO course){
        return ResponseEntity.ok(coursesService.save(course));
    }


    @Operation(summary = "Update an existing course", description = "Updates an existing course.")
 @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "409", description = "Conflict",content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable Long id, @RequestBody CourseDTO course){
        return ResponseEntity.ok(coursesService.update(id,course));
    }

    @Operation(summary = "Delete a course by ID", description = "Deletes a course by its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "No Content",content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        coursesService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
