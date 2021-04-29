package com.example.userapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Initialized the database with basic User objects. Great for testing and demonstrating functionality of code.
 */
@Configuration
class LoadDatabase {

    // Logger is used to print out that the users have been create
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("John", "Smith")));
            log.info("Preloading " + repository.save(new User("Matt", "Zulu")));
            log.info("Preloading " + repository.save(new User("Lucy", "Betterton")));
        };
    }
}
