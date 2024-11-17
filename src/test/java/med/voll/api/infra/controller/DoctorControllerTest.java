package med.voll.api.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import med.voll.api.domain.Address;
import med.voll.api.domain.application.usecases.doctor.CreateDoctor;
import med.voll.api.domain.application.usecases.doctor.DeleteDoctor;
import med.voll.api.domain.application.usecases.doctor.ListDoctor;
import med.voll.api.domain.application.usecases.doctor.UpdateDoctor;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.enums.Specialty;
import med.voll.api.infra.controller.doctor.DoctorController;
import med.voll.api.infra.controller.doctor.DoctorDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DoctorControllerTest {

    @Mock
    private CreateDoctor createDoctor;

    @Mock
    private ListDoctor listDoctor;

    @Mock
    private UpdateDoctor updateDoctor;

    @Mock
    private DeleteDoctor deleteDoctor;

    @InjectMocks
    private DoctorController controller;

    private DoctorDTO doctorDTO;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        objectMapper = new ObjectMapper();

        Address address = new Address("xxxx", "yyyy",
                "09123456", "Osasco", "SP", "10", "xxx");

        doctorDTO = new DoctorDTO("Júnior Lima",
                "teste@email.com.br", "11901234567",
                "12345678", Specialty.DERMATOLOGIA, address, true);
    }

    @Test
    void shouldReturCreateDoctor() throws Exception {

        Doctor saveDoctor = new Doctor(doctorDTO.name(), doctorDTO.email(), doctorDTO.phoneNumber(),
                doctorDTO.crm(), doctorDTO.specialty(), doctorDTO.address(), true);

        Mockito.when(createDoctor.createDoctor(Mockito.any(Doctor.class))).thenReturn(saveDoctor);

        mockMvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Júnior Lima"))
                .andExpect(jsonPath("$.email").value("teste@email.com.br"))
                .andExpect(jsonPath("$.phoneNumber").value("11901234567"))
                .andExpect(jsonPath("$.crm").value("12345678"))
                .andExpect(jsonPath("$.specialty").value("DERMATOLOGIA"))
                .andExpect(jsonPath("$.address.street").value("xxxx"))
                .andExpect(jsonPath("$.address.district").value("yyyy"))
                .andExpect(jsonPath("$.address.postalCode").value("09123456"))
                .andExpect(jsonPath("$.address.city").value("Osasco"))
                .andExpect(jsonPath("$.address.uf").value("SP"))
                .andExpect(jsonPath("$.address.number").value("10"))
                .andExpect(jsonPath("$.address.adjunct").value("xxx"))
                .andExpect(jsonPath("$.active").value(true));

        Mockito.verify(createDoctor,
                Mockito.times(1)).createDoctor(Mockito.any(Doctor.class));

    }

    @Test
    void shouldReturnListAllDoctors() throws Exception {
        List<Doctor> doctors = List.of(
                new Doctor(doctorDTO.name(), doctorDTO.email(), doctorDTO.phoneNumber(),
                        doctorDTO.crm(), doctorDTO.specialty(), null, true));

        Mockito.when(listDoctor.listAllDoctors(Mockito.anyInt())).thenReturn(doctors);

        mockMvc.perform(get("/medicos?page=0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Júnior Lima"))
                .andExpect(jsonPath("$.content[0].email").value("teste@email.com.br"))
                .andExpect(jsonPath("$.content[0].phoneNumber").value("11901234567"))
                .andExpect(jsonPath("$.content[0].crm").value("12345678"))
                .andExpect(jsonPath("$.content[0].specialty").value("DERMATOLOGIA"))
                .andExpect(jsonPath("$.content[0].active").value(true));

        Mockito.verify(listDoctor,
                Mockito.times(1)).listAllDoctors(0);
    }

    @Test
    void shouldReturUpdatedDoctor() throws Exception {

        Doctor saveDoctor = new Doctor(doctorDTO.name(), doctorDTO.email(), doctorDTO.phoneNumber(),
                doctorDTO.crm(), doctorDTO.specialty(), doctorDTO.address(), true);

        Mockito.when(updateDoctor.updateDoctor(Mockito.any(Doctor.class))).thenReturn(saveDoctor);

        mockMvc.perform(put("/medicos/12345678")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Júnior Lima"))
                .andExpect(jsonPath("$.phoneNumber").value("11901234567"))
                .andExpect(jsonPath("$.address.street").value("xxxx"))
                .andExpect(jsonPath("$.address.district").value("yyyy"))
                .andExpect(jsonPath("$.address.postalCode").value("09123456"))
                .andExpect(jsonPath("$.address.city").value("Osasco"))
                .andExpect(jsonPath("$.address.uf").value("SP"))
                .andExpect(jsonPath("$.address.number").value("10"))
                .andExpect(jsonPath("$.address.adjunct").value("xxx"));

        Mockito.verify(updateDoctor,
                Mockito.times(1)).updateDoctor(Mockito.any(Doctor.class));

    }

    @Test
    void shouldReturnDeletingDoctor() throws Exception {
        Doctor saveDoctor = new Doctor(doctorDTO.name(), doctorDTO.email(), doctorDTO.phoneNumber(),
                doctorDTO.crm(), doctorDTO.specialty(), doctorDTO.address(), true);

        mockMvc.perform(delete("/medicos/12345678"))
                .andExpect(status().isOk());

        Mockito.verify(deleteDoctor,
                Mockito.times(1)).deleteDoctor(saveDoctor.getCrm());
    }

}
