package com.courses.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.service.EnrollmentsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentsController {

    private final EnrollmentsService enrollmentsService;

    public EnrollmentsController(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;
    }


    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAll(){
        List<EnrollmentDTO> enrollments = enrollmentsService.getAll();
        if (enrollments.isEmpty()) {
            throw new NotFoundException("No contents found");
        }

         return ResponseEntity.ok(enrollments);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getById(@PathVariable Long id){
        EnrollmentDTO enrollment = enrollmentsService.getById(id);
        return ResponseEntity.ok(enrollment);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> save(@RequestBody EnrollmentDTO enrollmentDTO){
        EnrollmentDTO addedEnrollment = enrollmentsService.save(enrollmentDTO);
        return ResponseEntity.ok(addedEnrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update (@PathVariable Long id, @RequestBody EnrollmentDTO enrollmentDTO){
        EnrollmentDTO updatedEnrollment = enrollmentsService.update(id, enrollmentDTO);
        return ResponseEntity.ok(updatedEnrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        enrollmentsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
