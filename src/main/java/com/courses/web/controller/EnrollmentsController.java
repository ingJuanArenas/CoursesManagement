package com.courses.web.controller;

import java.time.LocalDate;
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

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.service.EnrollmentsService;
import com.courses.persistence.model.EnrollmentStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentsController {

    private final EnrollmentsService enrollmentsService;

    public EnrollmentsController(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;
    }


    @Operation(summary = "Get all enrollments", description = "Returns a list of all enrollments.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAll(){
        List<EnrollmentDTO> enrollments = enrollmentsService.getAll();
        if (enrollments.isEmpty()) {
            throw new NotFoundException("No contents found");
        }

         return ResponseEntity.ok(enrollments);
    }
    

    @Operation(summary = "Get enrollment by id", description = "Returns an enrollment by its id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getById(@PathVariable Long id){
        EnrollmentDTO enrollment = enrollmentsService.getById(id);
        return ResponseEntity.ok(enrollment);
    }

    @Operation(summary = "Search enrollments by date", description = "Returns a list of enrollments by their date.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<List<EnrollmentDTO>> getAllByDate(@RequestParam("date")LocalDate date){
        List<EnrollmentDTO> enrollments = enrollmentsService.getAllByDate(date);
        if (enrollments.isEmpty()) {
            throw new NotFoundException("No contents found");
        }

        return ResponseEntity.ok(enrollments);
    }

    @Operation(summary = "Search enrollments by status", description = "Returns a list of enrollments by their status.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping("/status")
    public ResponseEntity<List<EnrollmentDTO>> getAllByStatus(@RequestParam("status")EnrollmentStatus status){
        List<EnrollmentDTO> enrollments = enrollmentsService.getAllByStatus(status);
        if (enrollments.isEmpty()) {
            throw new NotFoundException("No contents found");
        }

        return ResponseEntity.ok(enrollments);
    }

    @Operation(summary = "Search enrollments by course id", description = "Returns a list of enrollments by their course id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping("/course")
    public ResponseEntity<List<EnrollmentDTO>> getAllByCourseId(@RequestParam("courseId")Long courseId){
        List<EnrollmentDTO> enrollments = enrollmentsService.getAllByCourseId(courseId);
        if (enrollments.isEmpty()) {
            throw new NotFoundException("No contents found");
        }
        return ResponseEntity.ok(enrollments);
    }

    @Operation(summary = "Search enrollments by student id", description = "Returns a list of enrollments by their student id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @GetMapping("/student")
    public ResponseEntity<List<EnrollmentDTO>> getAllByStudentId(@RequestParam("studentId")Long studentId){
        List<EnrollmentDTO> enrollments = enrollmentsService.getAllByStudentId(studentId);
        if (enrollments.isEmpty()) {
            throw new NotFoundException("No contents found");
        }
        return ResponseEntity.ok(enrollments);
    }

    @Operation(summary = "Save enrollment", description = "Saves a new enrollment.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Created"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @PostMapping
    public ResponseEntity<EnrollmentDTO> save(@RequestBody EnrollmentDTO enrollmentDTO){
        EnrollmentDTO addedEnrollment = enrollmentsService.save(enrollmentDTO);
        return ResponseEntity.ok(addedEnrollment);
    }

    @Operation(summary = "Update enrollment", description = "Updates an existing enrollment.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update (@PathVariable Long id, @RequestBody EnrollmentDTO enrollmentDTO){
        EnrollmentDTO updatedEnrollment = enrollmentsService.update(id, enrollmentDTO);
        return ResponseEntity.ok(updatedEnrollment);
    }


    @Operation(summary = "Delete enrollment", description = "Deletes an existing enrollment.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "No Content"),
        @ApiResponse(responseCode = "404", description = "Not Found",content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        enrollmentsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
