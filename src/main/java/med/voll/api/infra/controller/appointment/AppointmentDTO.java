package med.voll.api.infra.controller.appointment;

import com.fasterxml.jackson.annotation.JsonInclude;
import med.voll.api.enums.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AppointmentDTO(
        Long appointmentId,
        Long doctorId,
        Long patientId,
        String dateTime,
        Status status
) {
}
