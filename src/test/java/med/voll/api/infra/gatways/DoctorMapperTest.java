package med.voll.api.infra.gatways;

import med.voll.api.domain.Address;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.enums.Specialty;
import med.voll.api.infra.gateways.DoctorMapper;
import med.voll.api.infra.persistence.AddressEntity;
import med.voll.api.infra.persistence.DoctorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoctorMapperTest {

    private DoctorMapper mapper = new DoctorMapper();

    @Test
    void shouldReturnDomainToEntityConversion() {
        Address address = new Address("xxxx", "yyyy",
                "09123456", "Osasco", "SP", "10", "xxx");

        Doctor doctor = new Doctor("Júnior Lima",
                "teste@email.com.br", "11901234567",
                "12345678", Specialty.DERMATOLOGIA, address, true);

        DoctorEntity doctorEntity = mapper.toEntity(doctor);

        Assertions.assertEquals(doctorEntity.getName(), doctor.getName());
        Assertions.assertEquals(doctorEntity.getEmail(), doctor.getEmail());
        Assertions.assertEquals(doctorEntity.getCrm(), doctor.getCrm());
        Assertions.assertEquals(doctorEntity.getPhoneNumber(), doctor.getPhoneNumber());

    }

    @Test
    void shouldReturnEntityToDomainConversion() {
        AddressEntity address = new AddressEntity("xxxx", "yyyy",
                "09123456", "Osasco", "SP", "10", "xxx");

        DoctorEntity doctorEntity = new DoctorEntity("Júnior Lima",
                "teste@email.com.br", "11901234567",
                "12345678", Specialty.DERMATOLOGIA, address, true);

        Doctor doctor = mapper.toDomain(doctorEntity);

        Assertions.assertEquals(doctor.getName(), doctorEntity.getName());
        Assertions.assertEquals(doctor.getEmail(), doctorEntity.getEmail());
        Assertions.assertEquals(doctor.getCrm(), doctorEntity.getCrm());
        Assertions.assertEquals(doctor.getPhoneNumber(), doctorEntity.getPhoneNumber());

    }

}
