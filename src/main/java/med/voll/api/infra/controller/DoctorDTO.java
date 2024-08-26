package med.voll.api.infra.controller;

import med.voll.api.domain.Address;
import med.voll.api.enums.Specialty;

public record DoctorDTO(
         String name,
         String email,
         String crm,
         Specialty specialty,
         Address address
) {
}
