package ma.enset.patientsmvc.repositories;

import ma.enset.patientsmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {


    Page<Patient> findPatientsByNomContains(String keyword, Pageable pageable);

}
