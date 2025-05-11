package ru.smarkov.demo.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ExtendedCourseDto {
    private UUID id;
    private String title;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ExtendedCourseDto() {
    }

    public ExtendedCourseDto(UUID id, String title) {
        this.id = id;
        this.title = title;
    }
}
