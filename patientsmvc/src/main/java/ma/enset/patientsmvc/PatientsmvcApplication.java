package ma.enset.patientsmvc;

import com.github.javafaker.Faker;
import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import ma.enset.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class PatientsmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsmvcApplication.class, args);
    }


/*    @Bean*/
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){

        return  args ->{

            Faker faker = new Faker();
            Random rd = new Random();
            for (int i = 0; i < 100; i++) {
                patientRepository.save(new Patient(null,faker.name().name(),faker.date().birthday(), rd.nextBoolean(),rd.nextInt(100)));
            }
        };

    }

//    when we use bean , this methode will be called on the creation of object of the class
//        @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("hamza","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");
            securityService.saveNewUser("kamal","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("hamza","ADMIN");
            securityService.addRoleToUser("hamza","USER");
            securityService.addRoleToUser("hassan","USER");
            securityService.addRoleToUser("kamal","USER");
        };

    }


    //create a password encoder with bycrypt algorithme and store it in context
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
