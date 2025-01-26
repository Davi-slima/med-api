package med.voll.api.infra.controller.patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import med.voll.api.domain.application.usecases.patient.CreatePatient;
import med.voll.api.domain.application.usecases.patient.DeletePatient;
import med.voll.api.domain.application.usecases.patient.ListPatient;
import med.voll.api.domain.application.usecases.patient.UpdatePatient;
import med.voll.api.domain.entities.Patient;
import med.voll.api.infra.controller.UpdateDTORequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class PatientControllerTest {

    @Mock
    private CreatePatient createPatient;

    @Mock
    private ListPatient listPatient;

    @Mock
    private DeletePatient deletePatient;

    @Mock
    private UpdatePatient updatePatient;

    @InjectMocks
    private PatientController controller;

    private PatientDTO dto;
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();

        dto = new Gson()
                .fromJson(
                        "{\"name\":\"Alvaro Santos\",\"email\":\"alvaro.santos5@email.com\",\"phoneNumber\":\"11123456789\",\"cpf\":\"123.465.708-30\",\"address\":{\"street\":\"rua q\",\"district\":\"bairro\",\"postalCode\":\"12345600\",\"city\":\"Cajamar\",\"uf\":\"SP\",\"number\":\"100\",\"adjunct\":\"complemento\"}}",
                        PatientDTO.class
                );

    }

    @Test
    void shouldReturnCreatePatient() throws Exception {
        Patient savePatient = new Patient(null, dto.name(), dto.email(), dto.phoneNumber(), dto.cpf(), true, dto.address());

        Mockito.when(createPatient.createPatient(Mockito.any())).thenReturn(savePatient);

        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        Mockito.verify(createPatient,
                Mockito.times(1)).createPatient(Mockito.any(Patient.class));
    }

    @Test
    void shouldReturnListAllPatients() throws Exception {
        List<Patient> patients = List.of(new Patient(null, dto.name(), dto.email()
                , dto.phoneNumber(), dto.cpf(), true, dto.address()));

        Mockito.when(listPatient.listAllPatient(Mockito.anyInt())).thenReturn(patients);

        mockMvc.perform(get("/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnUpdateDoctor() throws Exception {
        UpdateDTORequest request = new UpdateDTORequest(dto.id(), dto.name(), dto.phoneNumber(), dto.address());

        Patient patient = new Patient(null, dto.name(), dto.email(), dto.phoneNumber(), dto.cpf(), true, dto.address());

        Mockito.when(updatePatient.updatePatient(Mockito.any())).thenReturn(patient);

        mockMvc.perform(put("/patients/123.465.708-30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());

        Mockito.verify(updatePatient, Mockito.times(1))
                .updatePatient(Mockito.any(Patient.class));
    }

    @Test
    void shouldReturnDeletePatient() throws Exception {
        Patient patient = new Patient(null, dto.name(), dto.email(), dto.phoneNumber(), dto.cpf(), true, dto.address());

        mockMvc.perform(delete("/patients/123.465.708-30")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(deletePatient, Mockito.times(1))
                .deletePatient(patient.getCpf());
    }
}
