package com.alexeyrand.task.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // входная точка в проект (метод main)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
