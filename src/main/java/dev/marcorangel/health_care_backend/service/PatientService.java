package dev.marcorangel.health_care_backend.service;

import dev.marcorangel.health_care_backend.model.Patient;
import dev.marcorangel.health_care_backend.repository.ApplicationUserRepository;
import dev.marcorangel.health_care_backend.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    private final ApplicationUserRepository applicationUserRepository;

    public String register(String patient_name, String patient_email, String patient_mobile, Date registeredDate) {
        String patient_Id = "";
        try {
            Patient patient = new Patient(patient_name, patient_email, patient_mobile, registeredDate);
            patientRepository.save(patient);
            patient_Id = String.valueOf(patient.getPatient_Id());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient_Id;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatient(String patient_id) {
        Optional<Patient> patient = patientRepository.findById(patient_id);
        return patient.orElseGet(patient::get);
    }

    public void delete(String patient_id) {
        patientRepository.deleteById(patient_id);
    }
}
