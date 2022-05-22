package ma.enset.ecom;

import ma.enset.ecom.entities.Categroy;
import ma.enset.ecom.entities.Product;
import ma.enset.ecom.repositories.CategoryRepository;
import ma.enset.ecom.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.github.javafaker.Faker;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class EcomApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository) {

        return args -> {
            System.out.println("test");

            Faker faker = new Faker();
            Random rd = new Random();
            productRepository.save(new Product(UUID.randomUUID().toString(),"Cam",12.4,12.1));

            Categroy categroy = new Categroy();
            categroy.setName("computers");
            categoryRepository.save(categroy);





        };

        };

    }


