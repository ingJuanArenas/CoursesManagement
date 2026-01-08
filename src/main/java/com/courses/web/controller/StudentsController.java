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

import com.courses.domain.dtos.StudentDTO;
import com.courses.domain.exceptions.NotFoundException;
import com.courses.domain.service.StudentsService;

@RestController
@RequestMapping("/api/students")
public class StudentsController {
    
    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll(){
        List<StudentDTO> students = studentsService.getAll();
        if (students.isEmpty()) {
            throw new NotFoundException("No contents found");
        }

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(studentsService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> getByName(@RequestParam String name){
        List<StudentDTO> students = studentsService.getByName(name);
        if (students.isEmpty()) {
            throw new NotFoundException("No courses found");
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentsService.save(studentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentsService.update(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        studentsService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
