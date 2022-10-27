package ru.otus.spring;

import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.TestingService;

@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.otus.spring")
@Configuration
public class App {

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        TestingService testingService = context.getBean(TestingService.class);
        testingService.run();
        context.close();
    }
}
