package med.voll.api.infra.controller.appointment;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AppointmentRequestDTO (
        Long doctorId,
        Long patientId,
        String dateTime
) {
}
