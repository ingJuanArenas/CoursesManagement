package com.courses.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.courses.domain.dtos.StudentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.service.StudentsService;
import com.courses.persistence.projections.CoursesSummary;
import com.courses.persistence.projections.StudentsSummary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
@EnableMethodSecurity(securedEnabled = true)
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
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Page<StudentsSummary>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
         Page<StudentsSummary> students = studentsService.getAll(page, size);
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
    @Secured("ROLE_ADMIN") // SOON : ONLY STUDENTS CAN SEE THEIR PROFILES AVOIDING BROCKEN ACCESS CONTROL
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(studentsService.getById(id));
    }


    @Operation(summary = "Search students by name", description = "Returns students by name.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/search")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Page<StudentsSummary>> getByName(@RequestParam String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<StudentsSummary> students = studentsService.getByName(name, page, size);
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
    @Secured("ROLE_ADMIN") // SOON : ONLY STUDENTS CAN SEE THEIR COURSES AVOIDING BROCKEN ACCESS CONTROL
    public ResponseEntity<Page<CoursesSummary>> getCoursesByStudentId(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
         Page<CoursesSummary> courses = studentsService.getCoursesByStudentId(id, page, size);
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
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudentDTO> save(@Valid@RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentsService.save(studentDTO));
    }

    @Operation(summary = "Update student", description = "Updates an existing student.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "409", description = "Conflict", content = @Content)
    })
    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN") // SOON : ONLY STUDENTS CAN UPDATE THEIR PROFILES AVOIDING BROCKEN ACCESS CONTROL
    public ResponseEntity<StudentDTO> update(@PathVariable Long id,@Valid @RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentsService.update(id, studentDTO));
    }

    @Operation(summary = "Update student status", description = "Updates the status of an existing student.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping("/{id}/status")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<StudentDTO> updateStatus(@PathVariable Long id, @RequestParam boolean active){
        return ResponseEntity.ok(studentsService.updateStatus(id, active));
    }

    @Operation(summary = "Delete student", description = "Deletes an existing student.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        studentsService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
