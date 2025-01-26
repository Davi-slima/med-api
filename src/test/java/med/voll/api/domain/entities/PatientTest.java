package med.voll.api.domain.entities;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PatientTest {

    @Test
    void shouldCreatePatient() {
        Patient patient = new Gson().fromJson("{\"name\":\"Alvaro Santos\",\"email\":\"alvaro.santos5@email.com\",\"phoneNumber\":\"11123456789\",\"cpf\":\"123.465.708-30\",\"address\":{\"street\":\"rua q\",\"district\":\"bairro\",\"postalCode\":\"12345600\",\"city\":\"Cajamar\",\"uf\":\"SP\",\"number\":\"100\",\"adjunct\":\"complemento\"}}", Patient.class);

        Assertions.assertEquals("Alvaro Santos", patient.getName());
        Assertions.assertEquals("Cajamar", patient.getAddress().getCity());
    }

}
