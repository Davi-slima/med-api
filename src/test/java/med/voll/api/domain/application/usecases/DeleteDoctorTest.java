package med.voll.api.domain.application.usecases;

import med.voll.api.domain.Address;
import med.voll.api.domain.application.gateway.DoctorRepository;
import med.voll.api.domain.application.usecases.DeleteDoctor;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.enums.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DeleteDoctorTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DeleteDoctor deleteDoctor;

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
    void shouldDeleteDoctorUsingCrm() {

        deleteDoctor.deleteDoctor(doctor.getCrm());

        Mockito.verify(doctorRepository,
                Mockito.times(1)).deleteDoctor(doctor.getCrm());

    }

}
