package ru.otus.spring.config;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.TestingService;

@Configuration
public class ApplicationConfig {

    @Bean
    public SmartInitializingSingleton importProcessor(TestingService testingService) {
        return testingService::run;
    }
}
