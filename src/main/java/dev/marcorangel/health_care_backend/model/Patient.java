package dev.marcorangel.health_care_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Patient {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String patient_Id = UUID.randomUUID().toString();
    private String patient_name;
    private String patient_email;
    private String patient_mobile;
    private Date registeredDate;


    public Patient(String patient_name, String patient_email, String patient_mobile, Date registeredDate) {
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_mobile = patient_mobile;
        this.registeredDate = registeredDate;
    }

    public Patient() {
        super();
    }

}
