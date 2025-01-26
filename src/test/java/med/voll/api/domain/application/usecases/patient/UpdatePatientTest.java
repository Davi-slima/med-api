package med.voll.api.domain.application.usecases.patient;

import com.google.gson.Gson;
import med.voll.api.domain.application.gateway.patient.PatientGatewayRepository;
import med.voll.api.domain.entities.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class UpdatePatientTest {

    @Mock
    private PatientGatewayRepository repository;

    @InjectMocks
    private UpdatePatient updatePatient;

    private Patient patient;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        patient = new Gson().fromJson("{\"name\":\"Alvaro Santos\",\"email\":\"alvaro.santos5@email.com\",\"phoneNumber\":\"11123456789\",\"cpf\":\"123.465.708-30\",\"address\":{\"street\":\"rua q\",\"district\":\"bairro\",\"postalCode\":\"12345600\",\"city\":\"Cajamar\",\"uf\":\"SP\",\"number\":\"100\",\"adjunct\":\"complemento\"}}", Patient.class);

    }

    @Test
    void shouldUpdatePatient() {
        Mockito.when(repository.updatePatient(Mockito.any())).thenReturn(patient);
        Patient response = updatePatient.updatePatient(patient);

        Assertions.assertNotNull(updatePatient);
        Assertions.assertEquals("Alvaro Santos", response.getName());

    }

}
