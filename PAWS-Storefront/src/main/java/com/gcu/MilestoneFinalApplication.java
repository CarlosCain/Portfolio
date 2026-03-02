package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The main application class for Milestone 8.
 */
@SpringBootApplication
@ComponentScan({"com.gcu"})
public class MilestoneFinalApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MilestoneFinalApplication.class, args);
    }
}