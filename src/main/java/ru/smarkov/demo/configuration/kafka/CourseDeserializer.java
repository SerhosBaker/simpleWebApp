package ru.smarkov.demo.configuration.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import ru.smarkov.demo.domain.course.dto.CourseDto;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CourseDeserializer implements Deserializer<CourseDto> {
    @Override
    public CourseDto deserialize(String s, byte[] bytes) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String courseAsString = new String(bytes, StandardCharsets.UTF_8);

            return objectMapper.readValue(courseAsString, CourseDto.class);
        } catch (Exception e) {
            return null;
        }
    }
}
