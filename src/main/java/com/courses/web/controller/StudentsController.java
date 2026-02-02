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
import com.courses.domain.dtos.StudentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.projections.CoursesSummary;
import com.courses.domain.projections.StudentsSummary;
import com.courses.domain.service.StudentsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/students")
public class StudentsController {
    
    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Operation(summary = "Get all active students", description = "Returns a list of all active students.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<StudentsSummary>> getAll(){
        List<StudentsSummary> students = studentsService.getAll();
        if (students.isEmpty()) {
            throw new NotFoundException("No contents found");
        }

        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Get student by id", description = "Returns a student by id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(studentsService.getById(id));
    }


    @Operation(summary = "Search students by name", description = "Returns students by name.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> getByName(@RequestParam String name){
        List<StudentDTO> students = studentsService.getByName(name);
        if (students.isEmpty()) {
            throw new NotFoundException("No students found");
        }
        return ResponseEntity.ok(students);
    }




    @Operation(summary = "Get courses by student id", description = "Returns courses by student id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CoursesSummary>> getCoursesByStudentId(@PathVariable Long id){
        List<CoursesSummary> courses = studentsService.getCoursesByStudentId(id);
        if (courses.isEmpty()) {
            throw new NotFoundException("No courses found for student with id: " + id);
        }
        return ResponseEntity.ok(courses);
    }

    @Operation(summary = "Save student", description = "Saves a new student.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Created"),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content)
    })
    @PostMapping
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentsService.save(studentDTO));
    }

    @Operation(summary = "Update student", description = "Updates an existing student.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentsService.update(id, studentDTO));
    }


    @Operation(summary = "Delete student", description = "Deletes an existing student.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        studentsService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
