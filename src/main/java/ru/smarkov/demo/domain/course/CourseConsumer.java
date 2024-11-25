package ru.smarkov.demo.domain.course;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.smarkov.demo.domain.course.dto.CourseDto;

import java.util.List;

@Service
public class CourseConsumer {
    @KafkaListener(topics = "education.activity-progress", containerFactory = "kafkaListenerContainerFactory")
    public void handleCourse(ConsumerRecord<String, CourseDto> record) {
        CourseDto courseDto = record.value();
        if (courseDto != null) {
            System.out.println("key: " + record.key() + " value: course title" + record.value().getTitle());
        }
    }
}
