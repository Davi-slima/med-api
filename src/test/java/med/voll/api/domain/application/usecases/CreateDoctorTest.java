package med.voll.api.domain.application.usecases;

import med.voll.api.domain.Address;
import med.voll.api.domain.application.gateway.DoctorRepository;
import med.voll.api.domain.application.usecases.CreateDoctor;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.enums.Specialty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CreateDoctorTest {

    @Mock
    private DoctorRepository repository;

    @InjectMocks
    private CreateDoctor createDoctor;

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
    void shouldCreateDoctor() {
        Mockito.when(repository.createDoctor(Mockito.any())).thenReturn(doctor);
        Doctor responseDoctor = createDoctor.createDoctor(doctor);

        Assertions.assertNotNull(createDoctor);
        Assertions.assertEquals("Júnior Lima", responseDoctor.getName());
    }

}
