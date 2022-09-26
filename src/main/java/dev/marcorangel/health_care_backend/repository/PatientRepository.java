package dev.marcorangel.health_care_backend.repository;

import dev.marcorangel.health_care_backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,String> {

}
