package med.voll.api.infra.gateways;

import med.voll.api.domain.Address;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.infra.persistence.AddressEntity;
import med.voll.api.infra.persistence.DoctorEntity;

public class DoctorMapper {

    public DoctorEntity toEntity(Doctor doctor) {
        return new DoctorEntity(
                doctor.getName(), doctor.getEmail(), doctor.getCrm(),
                doctor.getSpecialty(), toAddressEntity(doctor.getAddress()));
    }

    public Doctor toDomain(DoctorEntity entity) {
        return new Doctor(
                entity.getName(), entity.getEmail(), entity.getCrm(),
                entity.getSpecialty(), toAddressDomain(entity.getAddress()));
    }

    private Address toAddressDomain(AddressEntity entity) {
        return new Address(
                entity.getStreet(), entity.getDistrict(), entity.getPostalCode(),
                entity.getCity(), entity.getUf(), entity.getNumber(), entity.getAdjunct());
    }

    private AddressEntity toAddressEntity(Address address) {
        return new AddressEntity(
                address.getStreet(), address.getDistrict(), address.getPostalCode(),
                address.getCity(), address.getUf(), address.getNumber(), address.getAdjunct());
    }

}
