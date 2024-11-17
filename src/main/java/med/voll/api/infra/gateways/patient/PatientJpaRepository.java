package med.voll.api.infra.gateways.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatwayRepository;
import med.voll.api.domain.entities.Patient;
import med.voll.api.infra.persistence.patient.PatientEntity;
import med.voll.api.infra.persistence.patient.PatientRepository;

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
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }

    @Override
    public void deletePatient(String cpf) {

    }

}
