package med.voll.api.infra.gateways.patient;

import med.voll.api.domain.Address;
import med.voll.api.domain.entities.Patient;
import med.voll.api.infra.persistence.AddressEntity;
import med.voll.api.infra.persistence.patient.PatientEntity;

public class PatientMapper {

    PatientEntity toEntity(Patient patient) {
        return new PatientEntity(patient.getName(), patient.getEmail(), patient.getPhoneNumber(),
                patient.getCpf(), toAddressEntity(patient.getAddress()), patient.isActive());
    }

    Patient toDomain(PatientEntity entity) {
        return new Patient(entity.getName(), entity.getEmail(), entity.getPhoneNumber(),
                entity.getCpf(), entity.isActive(), toAddressDomain(entity.getAddress()));
    }

    AddressEntity toAddressEntity(Address address) {
        return new AddressEntity(address.getStreet(), address.getDistrict(), address.getPostalCode(),
                address.getCity(), address.getUf(), address.getNumber(), address.getAdjunct());
    }

    Address toAddressDomain(AddressEntity entity) {
        return new Address(entity.getStreet(), entity.getDistrict(), entity.getPostalCode(),
                entity.getCity(), entity.getUf(), entity.getNumber(), entity.getAdjunct());
    }

}