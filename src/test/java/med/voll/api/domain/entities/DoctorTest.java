package med.voll.api.domain.entities;

import med.voll.api.domain.Address;
import med.voll.api.enums.Specialty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoctorTest {

    @Test
    void shouldCreateDoctor() {
        Address address = new Address("xxxx", "yyyy", "123456789", "teste", "SP", "01","Teste");

        Doctor doctor = new Doctor(1L, "Júnior Lima", "junior.lima@email.com",
                "11900000000", "12345678", Specialty.DERMATOLOGIA,address,
                true);

        Assertions.assertEquals("Júnior Lima", doctor.getName());
        Assertions.assertEquals("SP", address.getUf());

    }
}
