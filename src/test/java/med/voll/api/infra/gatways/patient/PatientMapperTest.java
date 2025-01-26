package med.voll.api.infra.gatways.patient;

import med.voll.api.domain.Address;
import med.voll.api.domain.entities.Patient;
import med.voll.api.infra.gateways.patient.PatientMapper;
import med.voll.api.infra.persistence.AddressEntity;
import med.voll.api.infra.persistence.patient.PatientEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientMapperTest {

    private PatientMapper mapper = new PatientMapper();

    @Test
    void shouldReturnDomainToEntityConversion() {
        Address address = new Address("rua q", "bairro", "12345600",
                "Cajamar", "SP", "100", "complemento");

        Patient patient = new Patient(null, "Alvaro Santos", "alvaro.santos5@email.com",
                "11123456789", "123.465.708-30", true, address);

        PatientEntity patientEntity = mapper.toEntity(patient);

        Assertions.assertEquals(patientEntity.getName(), patient.getName());
        Assertions.assertEquals(patientEntity.getEmail(), patient.getEmail());
        Assertions.assertEquals(patientEntity.getCpf(), patient.getCpf());
        Assertions.assertEquals(patientEntity.getPhoneNumber(), patient.getPhoneNumber());

    }

    @Test
    void shouldReturnEntityToDomainConversion() {
        AddressEntity address = new AddressEntity("xxxx", "yyyy",
                "09123456", "Osasco", "SP", "10", "xxx");

        PatientEntity patientEntity = new PatientEntity("Alvaro Santos", "alvaro.santos5@email.com",
                "11123456789", "123.465.708-30", address, true);

        Patient patient = mapper.toDomain(patientEntity);

        Assertions.assertEquals(patient.getName(), patientEntity.getName());
        Assertions.assertEquals(patient.getEmail(), patientEntity.getEmail());
        Assertions.assertEquals(patient.getCpf(), patientEntity.getCpf());
        Assertions.assertEquals(patient.getPhoneNumber(), patientEntity.getPhoneNumber());

    }

}
