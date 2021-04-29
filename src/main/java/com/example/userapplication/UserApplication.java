package com.example.userapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Implements a simple UserApplication using Spring Boot to create, delete, and display users with a RESTful architecture
 *
 * @author drewsansom
 * @version 1.0
 * @since 2021-4-29
 */
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
