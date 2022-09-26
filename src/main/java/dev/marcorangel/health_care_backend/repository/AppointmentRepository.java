package dev.marcorangel.health_care_backend.repository;

import dev.marcorangel.health_care_backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,String> {
    @Query(value = "SELECT e FROM Appointment e WHERE e.patientId = ?1")
    List<Appointment> findAllAppointmentsByPatientId(String patientId);
}
