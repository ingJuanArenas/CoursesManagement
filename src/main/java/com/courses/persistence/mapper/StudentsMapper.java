package com.courses.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.courses.domain.dtos.StudentDTO;
import com.courses.persistence.model.Student;

@Mapper(componentModel = "spring")
public interface StudentsMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    Student toEntity (StudentDTO studentDTO);

    StudentDTO toDto (Student student);
    List<StudentDTO> toDtos (List<Student> students);

    void UpdateEntityFromDto(StudentDTO courseDTO, @MappingTarget Student student);
}
