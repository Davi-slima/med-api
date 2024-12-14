package med.voll.api.infra.controller.doctor;

import com.fasterxml.jackson.annotation.JsonInclude;
import med.voll.api.domain.Address;
import med.voll.api.enums.Specialty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DoctorDTO(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String crm,
        Specialty specialty,
        Address address,
        boolean active
) {
}
