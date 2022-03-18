package ma.enset.hospital.web;

import ma.enset.hospital.entities.Consultation;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {

    final PatientRepository patientRepository;
    public PatientRestController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    ConsultationRepository consultationRepository;

    @GetMapping ("/patients")
    public List<Patient> patientList(){
      return  patientRepository.findAll();
    }

    @GetMapping ("/consultations")
    public List<Consultation> consultationList(){
        return  consultationRepository.findAll();
    }



}
