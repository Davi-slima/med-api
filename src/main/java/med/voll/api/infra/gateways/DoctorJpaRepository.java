package med.voll.api.infra.gateways;

import med.voll.api.domain.application.gateway.DoctorRepository;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.infra.persistence.DoctorEntity;
import med.voll.api.infra.persistence.MedicoRepository;

import java.util.List;

public class DoctorJpaRepository implements DoctorRepository {

    private final MedicoRepository repository;
    private final DoctorMapper mapper;

    public DoctorJpaRepository(MedicoRepository repository, DoctorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        DoctorEntity entity = mapper.toEntity(doctor);
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Doctor> ListAllDoctors() {
        return null;
    }

    @Override
    public Doctor UpdateDoctor(String crm, Doctor doctor) {
        return null;
    }

    @Override
    public void deleteDoctor(String crm) {

    }

}