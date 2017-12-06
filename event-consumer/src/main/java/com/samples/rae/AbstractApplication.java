package com.samples.rae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public abstract class AbstractApplication {

    @Autowired
    private Dispatcher dispatcher;

    public static void main(String[] args) {
        SpringApplication.run(AbstractApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> dispatcher.run();
    }

}