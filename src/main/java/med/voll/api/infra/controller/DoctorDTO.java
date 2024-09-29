package med.voll.api.infra.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import med.voll.api.domain.Address;
import med.voll.api.enums.Specialty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DoctorDTO(
        String name,
        String email,
        String phoneNumber,
        String crm,
        Specialty specialty,
        Address address
) {
}
