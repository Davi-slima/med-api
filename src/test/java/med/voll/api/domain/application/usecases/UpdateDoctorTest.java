package med.voll.api.domain.application.usecases;

import med.voll.api.domain.Address;
import med.voll.api.domain.application.gateway.DoctorRepository;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.enums.Specialty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UpdateDoctorTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private UpdateDoctor updateDoctor;

    private Doctor doctor;

    @BeforeEach
    void startup() {
        MockitoAnnotations.openMocks(this);

        Address address = new Address("xxxx", "yyyy",
                "09123456","Osasco","SP", "10", "xxx");

        doctor = new Doctor("Júnior Lima",
                "teste@email.com.br", "11901234567",
                "12345678", Specialty.DERMATOLOGIA, address, true);

    }

    @Test
    void shouldUpdateDoctorUsinCrm() {
        Mockito.when(doctorRepository.updateDoctor(Mockito.any())).thenReturn(doctor);
        Doctor doctorResponse = updateDoctor.updateDoctor(doctor);

        Assertions.assertNotNull(updateDoctor);
        Assertions.assertEquals("Júnior Lima", doctorResponse.getName());
        Assertions.assertEquals("12345678", doctorResponse.getCrm());

        Mockito.verify(doctorRepository,
                Mockito.times(1)).updateDoctor(doctor);
    }

}
