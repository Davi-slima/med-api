package med.voll.api.domain.entities;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.enums.Status;

@Getter
@Setter
public class Appointment {

    private Long appointmentId;
    private Long doctorId;
    private Long patientId;
    private String dateTime;
    private Status status;

    public Appointment(Long appointmentId, Long doctorId, Long patientId, String dateTime, Status status) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = dateTime;
        this.status = status;
    }
}