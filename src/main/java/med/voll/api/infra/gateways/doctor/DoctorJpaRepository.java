package med.voll.api.infra.gateways.doctor;

import med.voll.api.domain.application.gateway.doctor.DoctorGatwayRepository;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.infra.persistence.doctor.DoctorEntity;
import med.voll.api.infra.persistence.doctor.DoctorRepository;

import java.util.Comparator;
import java.util.List;

public class DoctorJpaRepository implements DoctorGatwayRepository {

    private final DoctorRepository repository;
    private final DoctorMapper mapper;

    public DoctorJpaRepository(DoctorRepository repository, DoctorMapper mapper) {
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
    public List<Doctor> listAllDoctors(int page) {
        int size = 10;
        return repository.findAll().stream()
                .sorted(Comparator.comparing(DoctorEntity::getName))
                .skip(page * size)
                .limit(size)
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        DoctorEntity entity = repository.findByCrm(doctor.getCrm());

        if (entity != null) {
            DoctorEntity updateDoctor = mapper.toEntity(doctor);
            updateDoctor.setId(entity.getId());
            repository.save(updateDoctor);
            return mapper.toDomain(updateDoctor);
        }

        return null;
    }

    @Override
    public void deleteDoctor(String crm) {
        DoctorEntity doctorEntity = repository.findByCrm(crm);
        doctorEntity.setActive(false);
        repository.save(doctorEntity);
        mapper.toDomain(doctorEntity);
    }

}
