package ru.smarkov.demo.configuration.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Map;
import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;

@Configuration
public class KafkaConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("kafka.education.producer")
    public KafkaProperties educationKafkaProperties() {
        return new KafkaProperties();
    }

    @Bean
    public KafkaProducer educationKafkaProducer(KafkaProperties educationKafkaProperties) {
        return new KafkaProducer(educationKafkaProperties.buildProducerProperties());
    }
}
