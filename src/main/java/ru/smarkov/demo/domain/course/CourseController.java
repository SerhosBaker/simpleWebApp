package ru.smarkov.demo.domain.course;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarkov.demo.configuration.kafka.DemoProducerCallback;
import ru.smarkov.demo.domain.course.dto.CourseDto;
import ru.smarkov.demo.domain.course.dto.ExtendedCourseDto;
import ru.smarkov.demo.domain.course.mapper.SimpleSourceDestinationMapper;
import ru.smarkov.demo.domain.person.Hero;
import ru.smarkov.demo.domain.person.Scorpion;
import ru.smarkov.demo.domain.person.Subzero;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.UUID.randomUUID;

@Controller
@RestController
@RequestMapping("/course")

public class CourseController {
    public static final String EDUCATION_ACTIVITY_PROGRESS_TOPIC = "education.activity-progress";

    @Autowired
    private SimpleSourceDestinationMapper mapper;

    @Autowired
    private Subzero subzero;

    @Autowired
    private KafkaProducer<String, CourseDto> producer;

    @Autowired
    private KafkaConsumer<String, CourseDto> consumer;

    @GetMapping("/first")
    public CourseDto home() {
        ExtendedCourseDto extCourse = new ExtendedCourseDto(randomUUID(), "course 1 the best");

        Scorpion sc = new Scorpion();

        if (sc.getClass().isAnnotationPresent(Hero.class)) {
            System.out.println("Hero name: " + sc.getClass().getAnnotation(Hero.class).name());
            System.out.println("best name:" + sc.getBestName());
        }

        System.out.println("Hero name: " + subzero.getName());
        System.out.println("best live:" + subzero.getLive());

        return mapper.destinationToSource(extCourse);
    }

    @PostMapping("/sendKafka")
    public void sendKafka() {

        CourseDto course = new CourseDto(randomUUID(), "First course about stocks");

        ProducerRecord<String, CourseDto> record = new ProducerRecord<>(
                "education.activity-progress",
                "test record key sync",
                course);
        try {
            producer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/sendKafkaAsync")
    public void sendKafkaAsync() {
        CourseDto course = new CourseDto(randomUUID(), "First course about stocks");

        ProducerRecord<String, CourseDto> record = new ProducerRecord<>(
                EDUCATION_ACTIVITY_PROGRESS_TOPIC,
                "test record key async",
                course);
        // "test record key".getBytes()
        try {
            producer.send(record, new DemoProducerCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/subtractOffset")
    public void subtractOffset() {
        consumer.subscribe(List.of(EDUCATION_ACTIVITY_PROGRESS_TOPIC));
        List<PartitionInfo> partitions = consumer.partitionsFor(EDUCATION_ACTIVITY_PROGRESS_TOPIC);

        List<TopicPartition> topicPartitions = partitions.stream()
                .map(PartitionInfo::partition)
                .map(partNum -> new TopicPartition(EDUCATION_ACTIVITY_PROGRESS_TOPIC, partNum))
                .toList();

        Map<TopicPartition, Long> topicOffsets = consumer.endOffsets(topicPartitions);

        printTopicOffsets(topicOffsets);

        //   consumer.seek(topicPartitions.get(0), 22930);

        Map<TopicPartition, Long> topicOffsetsNew = consumer.endOffsets(topicPartitions);
        printTopicOffsets(topicOffsetsNew);
    }

    /// Не много не жизнеспособный пример
    /// Начнем читать топик с конца
    /// Вычитаем последние офсеты со всех партиций
    /// Затем сделаем offset commit
    /// будет ли смещен оффсет?
    /// начнет ли лисенер заново получать сообщения? начинает, но с большой задержкой. Почему??
    @PostMapping("/subtractOffset2")
    public void subtractOffset2() {
        consumer.subscribe(List.of(EDUCATION_ACTIVITY_PROGRESS_TOPIC));
        consumer.poll(0);

        Set<TopicPartition> partitions = consumer.assignment();
        List<TopicPartition> parts = partitions.stream().toList();
        consumer.seek(parts.get(0), 22930);
       // consumer.poll(0);

        List<PartitionInfo> partitionsAgain = consumer.partitionsFor(EDUCATION_ACTIVITY_PROGRESS_TOPIC);

        List<TopicPartition> topicPartitions = partitionsAgain.stream()
                .map(PartitionInfo::partition)
                .map(partNum -> new TopicPartition(EDUCATION_ACTIVITY_PROGRESS_TOPIC, partNum))
                .toList();

        Map<TopicPartition, Long> topicOffsets = consumer.endOffsets(topicPartitions);

        printTopicOffsets(topicOffsets);
        consumer.commitSync();
    }

    private void printTopicOffsets(Map<TopicPartition, Long> topicOffsets) {
        // назначим новые смещения& предварительно проверяем, что такое смещение есть
        topicOffsets.forEach((key, value) -> System.out.printf("part: %s offset: %s%n", key, value));
    }
//
//    private void v1(){
//        KStream<String, String> steam;
//    }
}
