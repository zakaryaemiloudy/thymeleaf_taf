package com.example.taf_thymeleaf;

import com.example.taf_thymeleaf.Entities.Client;
import com.example.taf_thymeleaf.Repositories.ClientRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication //(exclude = {SecurityAutoConfiguration.class})
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




    @Bean
    CommandLineRunner jbdcauthentification(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        return args -> {


            UserDetails u1 = User.withUsername("user1").password(passwordEncoder.encode("user")).roles("USER").build();
            UserDetails u2 = User.withUsername("user2").password(passwordEncoder.encode("user")).roles("USER").build();

            UserDetails a1 = User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER","ADMIN").build();

            jdbcUserDetailsManager.createUser(u1);
            jdbcUserDetailsManager.createUser(u2);
            jdbcUserDetailsManager.createUser(a1);

        };
    }










    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
