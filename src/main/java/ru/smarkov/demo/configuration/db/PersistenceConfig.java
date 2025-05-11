package ru.smarkov.demo.configuration.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.smarkov.demo.domain.event")
public class PersistenceConfig {
}
