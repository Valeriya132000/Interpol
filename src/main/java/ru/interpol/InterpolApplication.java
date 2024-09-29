package ru.interpol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InterpolApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterpolApplication.class, args);
    }
}