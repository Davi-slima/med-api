package med.voll.api.infra.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import med.voll.api.domain.Address;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateDTORequest(String name,
                               String phoneNumber,
                               Address address) {
}