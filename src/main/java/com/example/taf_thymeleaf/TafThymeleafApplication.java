package com.example.taf_thymeleaf;

import com.example.taf_thymeleaf.Entities.Client;
import com.example.taf_thymeleaf.Repositories.ClientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TafThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(TafThymeleafApplication.class, args);
    }


    @Bean
    CommandLineRunner start(ClientRepo clientRepo) {
        return args -> {
            clientRepo.save(

                    Client.builder().name("Mohamed").lastName("Hassan").age(25).build()

            );

            clientRepo.save(

                    Client.builder().name("zakaryae").lastName("miloudy").age(19).build()

            );


            clientRepo.save(

                    Client.builder().name("walid").lastName("hamroud").age(21).build()

            );

        };
    }
}
