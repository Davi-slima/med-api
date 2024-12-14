package med.voll.api.domain.application.usecases;

import med.voll.api.domain.Address;
import med.voll.api.domain.application.gateway.doctor.DoctorGatewayRepository;
import med.voll.api.domain.application.usecases.doctor.UpdateDoctor;
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
    private DoctorGatewayRepository doctorGatewayRepository;

    @InjectMocks
    private UpdateDoctor updateDoctor;

    private Doctor doctor;

    @BeforeEach
    void startup() {
        MockitoAnnotations.openMocks(this);

        Address address = new Address("xxxx", "yyyy",
                "09123456","Osasco","SP", "10", "xxx");

        doctor = new Doctor(1L, "Júnior Lima",
                "teste@email.com.br", "11901234567",
                "12345678", Specialty.DERMATOLOGIA, address, true);

    }

    @Test
    void shouldUpdateDoctorUsinCrm() {
        Mockito.when(doctorGatewayRepository.updateDoctor(Mockito.any())).thenReturn(doctor);
        Doctor doctorResponse = updateDoctor.updateDoctor(doctor);

        Assertions.assertNotNull(updateDoctor);
        Assertions.assertEquals("Júnior Lima", doctorResponse.getName());
        Assertions.assertEquals("12345678", doctorResponse.getCrm());

        Mockito.verify(doctorGatewayRepository,
                Mockito.times(1)).updateDoctor(doctor);
    }

}
