package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.services.IHospitalServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }


    @Bean
    CommandLineRunner start(IHospitalServiceImpl hospitalService) {

        return args -> {
            Faker faker = new Faker();
            Random rd = new Random();

            for (int i = 0; i < 10; i++) {
                hospitalService.savePatient(new Patient(faker.name().name(), faker.internet().emailAddress(), faker.date().birthday(), rd.nextBoolean()));
            }

            for (int i = 0; i < 10; i++) {
                hospitalService.saveMedecin(new Medecin(faker.name().name(), faker.internet().emailAddress(), faker.lorem().word()));
            }

            Patient patient = hospitalService.findPatientById(1L);
            Medecin medecin = hospitalService.findMedecinById(1L);
            hospitalService.saveRendezVous(new RendezVous(new Date(), StatusRDV.PENDING, patient, medecin));
            RendezVous rendezVous = hospitalService.findRendezVousById(1L);
            hospitalService.saveConsultation(new Consultation(new Date(), "rapport 1", rendezVous));

        };
    }

}
