package com.samples.rae;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BillPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillPaymentApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

        };
    }

}