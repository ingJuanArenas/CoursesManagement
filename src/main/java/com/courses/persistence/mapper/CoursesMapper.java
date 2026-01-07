package com.courses.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.courses.domain.dtos.CourseDTO;
import com.courses.persistence.model.Course;

@Mapper(componentModel = "spring")
public interface CoursesMapper {
    
    @Mapping(target = "id", ignore = true)
    Course toEntity (CourseDTO courseDTO);

    CourseDTO toDto (Course course);
    List<CourseDTO> toDtos (List<Course> courses);

    void updateEntityFromDto(CourseDTO courseDTO, @MappingTarget Course course);
}
