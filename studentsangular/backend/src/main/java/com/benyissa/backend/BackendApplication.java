package com.benyissa.backend;

import com.benyissa.backend.entities.Genre;
import com.benyissa.backend.entities.Student;
import com.benyissa.backend.repositories.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


//    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){

        return  args ->{
            Faker faker = new Faker();
            Random rd = new Random();
            for (int i = 0; i < 100; i++) {
                studentRepository.save(new Student(null,faker.name().name(),faker.name().firstName(),faker.internet().emailAddress(),faker.date().birthday(),rd.nextBoolean()? Genre.FEMININ : Genre.MASCULIN ,rd.nextBoolean()));
            }
            System.out.println("done");
        };
    }
}
