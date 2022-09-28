package dev.marcorangel.health_care_backend.controller;

import dev.marcorangel.health_care_backend.model.ApplicationUser;
import dev.marcorangel.health_care_backend.model.Patient;
import dev.marcorangel.health_care_backend.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<String> savePatient(@RequestBody Patient patient, @AuthenticationPrincipal ApplicationUser authUser) {
        patientService.register(patient, authUser);
        if (patientService.register(patient, authUser) == null)
            return ResponseEntity.status(400).body("Registration failure");

        return ResponseEntity.ok("Registration successful");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/view/{patient_id}")
    public ResponseEntity<Patient> getPatient(@PathVariable String patient_id) {
        return ResponseEntity.ok(patientService.getPatient(patient_id));
    }

    @DeleteMapping("/delete/{patient_id}")
    public ResponseEntity<String> deletePatient(@PathVariable String patient_id) {
        patientService.delete(patient_id);
        return ResponseEntity.ok("Patient deleted");
    }
}
