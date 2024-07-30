package ru.smarkov.demo.domain.course;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarkov.demo.domain.course.dto.CourseDto;
import ru.smarkov.demo.domain.course.dto.ExtendedCourseDto;
import ru.smarkov.demo.domain.course.mapper.SimpleSourceDestinationMapper;
import ru.smarkov.demo.domain.person.Hero;
import ru.smarkov.demo.domain.person.Scorpion;
import ru.smarkov.demo.domain.person.Subzero;
import ru.smarkov.demo.util.TestClass;

import java.util.UUID;

@Controller
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private SimpleSourceDestinationMapper mapper;

    @Autowired
    private Subzero subzero;

    @GetMapping("/first")
    public CourseDto home() {
        ExtendedCourseDto extCourse = new ExtendedCourseDto(UUID.randomUUID(), "course 1 the best");

        Scorpion sc = new Scorpion();

        if (sc.getClass().isAnnotationPresent(Hero.class)) {
            System.out.println("Hero name: " + sc.getClass().getAnnotation(Hero.class).name());
            System.out.println("best name:" + sc.getBestName());
        }

        System.out.println("Hero name: " + subzero.getName());
        System.out.println("best live:" + subzero.getLive());

        return mapper.destinationToSource(extCourse);
    }
}
