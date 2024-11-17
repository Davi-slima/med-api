package med.voll.api.infra.gateways.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatwayRepository;
import med.voll.api.domain.entities.Patient;
import med.voll.api.infra.persistence.patient.PatientEntity;
import med.voll.api.infra.persistence.patient.PatientRepository;

import java.util.Comparator;
import java.util.List;

public class PatientJpaRepository implements PatientGatwayRepository {

    private final PatientRepository repository;

    private final PatientMapper mapper;

    public PatientJpaRepository(PatientRepository repository, PatientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Patient createPatient(Patient patient) {
        PatientEntity entity = mapper.toEntity(patient);
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Patient> listAllPatient(int page) {
        int size = 10;

        return repository.findAll().stream()
                .sorted(Comparator.comparing(PatientEntity::getName))
                .skip(page * size)
                .limit(size)
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Patient updatePatient(Patient patient) {
        PatientEntity entity = repository.findByCpf(patient.getCpf());

        if (entity != null) {
            PatientEntity updatePatient = mapper.toEntity(patient);
            updatePatient.setId(entity.getId());
            repository.save(updatePatient);
            return mapper.toDomain(updatePatient);
        }

        return null;
    }

    @Override
    public void deletePatient(String cpf) {
        PatientEntity entity = repository.findByCpf(cpf);
        entity.setActive(false);
        repository.save(entity);
        mapper.toDomain(entity);

    }

}
