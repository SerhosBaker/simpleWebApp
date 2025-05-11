package ru.smarkov.demo.domain.course.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.smarkov.demo.domain.course.dto.CourseDto;
import ru.smarkov.demo.domain.course.dto.ExtendedCourseDto;

@Mapper(componentModel = "spring")
public interface SimpleSourceDestinationMapper {
    ExtendedCourseDto sourceToDestination(CourseDto source);

    CourseDto destinationToSource(ExtendedCourseDto destination);
}
