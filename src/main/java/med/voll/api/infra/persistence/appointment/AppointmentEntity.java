package med.voll.api.infra.persistence.appointment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.enums.Status;
import med.voll.api.infra.persistence.doctor.DoctorEntity;
import med.voll.api.infra.persistence.patient.PatientEntity;

@Getter
@Setter
@Entity
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    private String dateTime;

    private Status staus;

    public AppointmentEntity() {

    }

    public AppointmentEntity(DoctorEntity doctor, PatientEntity patient, String dateTime, Status staus) {
        this.doctor = doctor;
        this.patient = patient;
        this.dateTime = dateTime;
        this.staus = staus;
    }
}
