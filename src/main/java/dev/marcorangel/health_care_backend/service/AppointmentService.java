package dev.marcorangel.health_care_backend.service;

import dev.marcorangel.health_care_backend.model.Appointment;
import dev.marcorangel.health_care_backend.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    public Appointment save(Appointment appointment){
        return appointmentRepository.save(
                Appointment.builder()
                .disease(appointment.getDisease())
                .tentativeDate(appointment.getTentativeDate())
                .priority(appointment.getPriority())
                .patientId(appointment.getPatientId())
                .bookingTime(new Date())
                .build()
        );
    }

    public Appointment findById(String appId){
        Optional<Appointment> appointment = appointmentRepository.findById(appId);

        if(appointment.isEmpty())
            throw new IllegalArgumentException("Appointment not found!");

        return appointment.get();
    }

    public void deleteAppointment(String appintId) {
        appointmentRepository.deleteById(appintId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByIdPatient(String patientId){

        return appointmentRepository.findAllAppointmentsByPatientId(patientId);
    }



}
