package med.voll.api.domain.application.usecases.patient;

import com.google.gson.Gson;
import med.voll.api.domain.application.gateway.patient.PatientGatewayRepository;
import med.voll.api.domain.entities.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreatePatientTest {

    @Mock
    private PatientGatewayRepository repository;

    @InjectMocks
    private CreatePatient createPatient;

    private Patient patient;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        patient = new Gson().fromJson("{\"name\":\"Alvaro Santos\",\"email\":\"alvaro.santos5@email.com\",\"phoneNumber\":\"11123456789\",\"cpf\":\"123.465.708-30\",\"address\":{\"street\":\"rua q\",\"district\":\"bairro\",\"postalCode\":\"12345600\",\"city\":\"Cajamar\",\"uf\":\"SP\",\"number\":\"100\",\"adjunct\":\"complemento\"}}", Patient.class);

    }

    @Test
    void shouldCreatePatient() {
        when(repository.createPatient(any())).thenReturn(patient);
        Patient responsePatient = createPatient.createPatient(patient);

        assertNotNull(createPatient);
        assertEquals("Alvaro Santos", responsePatient.getName());
    }

}
