package med.voll.api.infra.gatways;

import med.voll.api.domain.Address;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.enums.Specialty;
import med.voll.api.infra.gateways.DoctorJpaRepository;
import med.voll.api.infra.gateways.DoctorMapper;
import med.voll.api.infra.persistence.DoctorEntity;
import med.voll.api.infra.persistence.MedicoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class DoctorJpaRepositoryTest {

    @Mock
    private MedicoRepository repository;

    @Mock
    private DoctorMapper mapper;

    @InjectMocks
    private DoctorJpaRepository jpaRepository;

    private Doctor doctor;
    private DoctorEntity doctorEntity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        Address address = new Address("xxxx", "yyyy",
                "09123456", "Osasco", "SP", "10", "xxx");

        doctor = new Doctor("Júnior Lima",
                "teste@email.com.br", "11901234567",
                "12345678", Specialty.DERMATOLOGIA, address, true);

        doctorEntity = new DoctorEntity();

    }

    @Test
    void shouldCallCreateDoctorMethod() {
        when(mapper.toEntity(doctor)).thenReturn(doctorEntity);
        when(repository.save(doctorEntity)).thenReturn(doctorEntity);
        when(mapper.toDomain(doctorEntity)).thenReturn(doctor);

        Doctor createDoctor = jpaRepository.createDoctor(doctor);

        Assertions.assertNotNull(createDoctor);
        Assertions.assertEquals("Júnior Lima", createDoctor.getName());

        verify(mapper).toDomain(doctorEntity);
        verify(repository).save(doctorEntity);
        verify(mapper).toEntity(doctor);
    }

    @Test
    void shouldCallListAllDoctorMethod() {
        doctorEntity.setName("Alice");
        doctorEntity.setEmail("alice@example.com");
        doctorEntity.setPhoneNumber("11999999999");
        doctorEntity.setSpecialty(Specialty.DERMATOLOGIA);
        doctorEntity.setCrm("123");
        doctorEntity.setActive(true);

        List<DoctorEntity> entities = List.of(doctorEntity);
        when(repository.findAll()).thenReturn(entities);

        when(mapper.toDomain(doctorEntity)).thenReturn(new Doctor("Alice", "alice@example.com", "11999999999", "123", Specialty.DERMATOLOGIA, null, true));

        List<Doctor> response = jpaRepository.listAllDoctors(0);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertTrue(response.stream().anyMatch(doc -> doc.getName().equals("Alice")));

        Assertions.assertEquals("Alice", response.get(0).getName());
    }


    @Test
    void shouldCallUpdateDoctorMethod() {
        when(mapper.toEntity(doctor)).thenReturn(doctorEntity);
        when(repository.findByCrm("12345678")).thenReturn(doctorEntity);
        when(repository.save(doctorEntity)).thenReturn(doctorEntity);
        when(mapper.toDomain(doctorEntity)).thenReturn(doctor);

        Doctor updateDoctor = jpaRepository.updateDoctor(doctor);

        Assertions.assertNotNull(updateDoctor);
        Assertions.assertEquals("Júnior Lima", updateDoctor.getName());

        verify(repository, times(1)).findByCrm("12345678");
        verify(repository, times(1)).save(doctorEntity);
        verify(mapper, times(1)).toEntity(doctor);
        verify(mapper, times(1)).toDomain(doctorEntity);
    }

    @Test
    void shouldReurnNullWhenDoctorNotFound() {

        when(repository.findByCrm("12345678")).thenReturn(null);
        Doctor updateDoctor = jpaRepository.updateDoctor(doctor);

        Assertions.assertNull(updateDoctor);

        verify(repository, times(1)).findByCrm("12345678");
        verify(repository, never()).save(doctorEntity);
        verify(mapper, never()).toEntity(any());
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void shouldCallDeleteDoctorMethod() {
        when(mapper.toEntity(doctor)).thenReturn(doctorEntity);
        when(repository.findByCrm("12345678")).thenReturn(doctorEntity);
        when(repository.save(doctorEntity)).thenReturn(doctorEntity);
        when(mapper.toDomain(doctorEntity)).thenReturn(doctor);

        jpaRepository.deleteDoctor("12345678");

        assertFalse(doctorEntity.isActive());
        verify(repository, times(1)).findByCrm("12345678");
        verify(repository, times(1)).save(doctorEntity);

    }

}
