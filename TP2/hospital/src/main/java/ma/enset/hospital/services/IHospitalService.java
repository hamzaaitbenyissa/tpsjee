package ma.enset.hospital.services;

import ma.enset.hospital.entities.Consultation;
import ma.enset.hospital.entities.Medecin;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.entities.RendezVous;


public interface IHospitalService {
    Patient savePatient(Patient patient);

    Medecin saveMedecin(Medecin medecin);

    RendezVous saveRendezVous(RendezVous rendezVous);

    Consultation saveConsultation(Consultation consultation);

    Patient findPatientById(Long id);
    Medecin findMedecinById(Long id);
    RendezVous findRendezVousById(Long id);
    Consultation findConsultationById(Long id);

}
