package med.voll.api.infra.gatways.patient;

import med.voll.api.domain.Address;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.Patient;
import med.voll.api.infra.gateways.patient.PatientJpaRepository;
import med.voll.api.infra.gateways.patient.PatientMapper;
import med.voll.api.infra.persistence.AddressEntity;
import med.voll.api.infra.persistence.patient.PatientEntity;
import med.voll.api.infra.persistence.patient.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class PatientJpaRepositoryTest {

    @Mock
    private PatientRepository repository;

    @InjectMocks
    private PatientJpaRepository jpaRepository;

    @Mock
    private PatientMapper mapper;

    private Patient patient;
    private PatientEntity entity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        Address address = new Address("rua q", "bairro", "12345600",
                "Cajamar", "SP", "100", "complemento");

        patient = new Patient(null, "Alvaro Santos", "alvaro.santos5@email.com",
                "11123456789", "123.465.708-30", true, address);

        entity = new PatientEntity();
    }

    @Test
    void shouldCallCreatePatientMethod() {
        when(mapper.toEntity(patient)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(patient);

        Patient createPatient = jpaRepository.createPatient(patient);

        Assertions.assertNotNull(createPatient);
        Assertions.assertEquals("Alvaro Santos", createPatient.getName());

        verify(mapper).toDomain(entity);
        verify(repository).save(entity);
        verify(mapper).toEntity(patient);
    }

    @Test
    void shouldCallListAllPatientMethod() {

        entity.setName("Alice");
        entity.setEmail("alice@example.com");
        entity.setPhoneNumber("11999999999");
        entity.setCpf("132.654.987-01");
        entity.setAddress(new AddressEntity("rua q", "bairro", "12345600",
                "Cajamar", "SP", "100", "complemento"));
        entity.setActive(true);

        List<PatientEntity> entities = List.of(entity);

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toDomain(entity)).thenReturn(new Patient(1L, "Alice", "alice@example.com", "11999999999", "132.654.987-01", true,
                new Address("rua q", "bairro", "12345600",
                        "Cajamar", "SP", "100", "complemento")));

        List<Patient> response = jpaRepository.listAllPatient(0);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertTrue(response.stream().anyMatch(doc -> doc.getName().equals("Alice")));

        Assertions.assertEquals("Alice", response.get(0).getName());
    }

    @Test
    void shouldCallUpdatePatientMethod() {
        when(mapper.toEntity(patient)).thenReturn(entity);
        when(repository.findByCpf("123.465.708-30")).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(patient);

        Patient updatePatient = jpaRepository.updatePatient(patient);

        Assertions.assertNotNull(updatePatient);
        Assertions.assertEquals("Alvaro Santos", updatePatient.getName());

        verify(repository, times(1)).findByCpf("123.465.708-30");
        verify(repository, times(1)).save(entity);
        verify(mapper, times(1)).toEntity(patient);
        verify(mapper, times(1)).toDomain(entity);
    }

    @Test
    void shouldReurnNullWhenPatientNotFound() {

        when(repository.findByCpf("123.465.708-30")).thenReturn(null);
        Patient updatePatient = jpaRepository.updatePatient(patient);

        Assertions.assertNull(updatePatient);

        verify(repository, times(1)).findByCpf("123.465.708-30");
        verify(repository, never()).save(entity);
        verify(mapper, never()).toEntity(any());
        verify(mapper, never()).toDomain(any());
    }
    @Test
    void shouldCallDeletePatientMethod() {
        when(mapper.toEntity(patient)).thenReturn(entity);
        when(repository.findByCpf("123.465.708-30")).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(patient);

        jpaRepository.deletePatient("123.465.708-30");

        assertFalse(entity.isActive());
        verify(repository, times(1)).findByCpf("123.465.708-30");
        verify(repository, times(1)).save(entity);

    }


}
