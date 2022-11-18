package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.IOServiceImpl;

@Configuration
public class IOConfig {
    @Bean
    public IOService getIOService() {
        return new IOServiceImpl(System.out, System.in);
    }
}
