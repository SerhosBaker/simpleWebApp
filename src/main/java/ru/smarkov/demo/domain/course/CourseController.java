package ru.smarkov.demo.domain.course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarkov.demo.domain.course.dto.CourseDto;

import java.util.UUID;

@Controller
@RestController
@RequestMapping("/course")
public class CourseController {

    @GetMapping("/first")
    public CourseDto home(){
        return new CourseDto(UUID.randomUUID(), "course 1");
    }
}
