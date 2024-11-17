package med.voll.api.infra.controller.patient;

import com.fasterxml.jackson.annotation.JsonInclude;
import med.voll.api.domain.Address;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatientDTO(
        String name,
        String email,
        String phoneNumber,
        String cpf,
        Address address,
        boolean isActive

) {
}
