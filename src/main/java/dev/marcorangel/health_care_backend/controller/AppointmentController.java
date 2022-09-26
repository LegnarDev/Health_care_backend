package dev.marcorangel.health_care_backend.controller;

import dev.marcorangel.health_care_backend.model.Appointment;
import dev.marcorangel.health_care_backend.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

    private AppointmentService appointmentService;

    @PostMapping("/register")
    public ResponseEntity<String> insertAppointment(@RequestBody Appointment appointment){

        if(appointment == null)
            return ResponseEntity.status(400).body("Registration failure");

        Appointment newAppointment = appointmentService.save(appointment);

        if(newAppointment == null)
            return ResponseEntity.status(400).body("Registration failure");

        return ResponseEntity.ok("Booking successfull");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }


    @GetMapping("/view/{appointmentId}")
    public ResponseEntity<Appointment> getPatientAppointments(@PathVariable String appointmentId){
        return ResponseEntity.ok(appointmentService.findById(appointmentId));
    }

    @GetMapping("/list/{patientid}")
    public ResponseEntity<List<Appointment>> getPatientAppointment(@PathVariable String patientid){
        return ResponseEntity.ok(appointmentService.getAppointmentsByIdPatient(patientid));
    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable String appointmentId){
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok("Appointment removed");
    }

}
