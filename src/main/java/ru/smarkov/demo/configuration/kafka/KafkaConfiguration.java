package ru.smarkov.demo.configuration.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.smarkov.demo.domain.course.dto.CourseDto;

import java.util.Map;

@Configuration
public class KafkaConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("kafka.education")
    public KafkaProperties educationKafkaProperties() {
        return new KafkaProperties();
    }

    @Bean
    public KafkaProducer<String, CourseDto> educationKafkaProducer(KafkaProperties educationKafkaProperties) {
        return new KafkaProducer<>(educationKafkaProperties.buildProducerProperties());
    }

    @Bean
    public KafkaConsumer<String, CourseDto> educationKafkaConsumer(KafkaProperties educationKafkaProperties) {
        return new KafkaConsumer<>(educationKafkaProperties.buildConsumerProperties());
    }

    @Bean
    public ConsumerFactory<String, CourseDto> consumerFactory(KafkaProperties educationKafkaProperties) {
        Map<String, Object> consumerProperties = educationKafkaProperties.buildConsumerProperties();
        consumerProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CourseDto> kafkaListenerContainerFactory(ConsumerFactory<String, CourseDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, CourseDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        // Дополнительные настройки, если необходимо:
        factory.setConcurrency(3); // Установите количество параллельных потоков, если требуется
        factory.getContainerProperties().setPollTimeout(3000); // Установите таймаут ожидания

        return factory;
    }
}
