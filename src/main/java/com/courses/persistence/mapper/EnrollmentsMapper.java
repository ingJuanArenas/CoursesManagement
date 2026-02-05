package com.courses.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.courses.domain.dtos.EnrollmentDTO;
import com.courses.persistence.model.Enrollment;

@Mapper(componentModel = "spring")
public interface EnrollmentsMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollmentDate", ignore = true)

    // 🔥 ESTOS SON LOS IMPORTANTES
    @Mapping(target = "courseId", source = "courseId")
    @Mapping(target = "studentId", source = "studentId")

    // relaciones se ignoran
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "student", ignore = true)

    Enrollment toEntity(EnrollmentDTO dto);

    EnrollmentDTO toDto(Enrollment enrollment);

}
