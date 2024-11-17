package med.voll.api.domain.application.usecases.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatwayRepository;
import med.voll.api.domain.entities.Patient;

import java.util.List;

public class ListPatient {

    private final PatientGatwayRepository repository;

    public ListPatient(PatientGatwayRepository repository) {
        this.repository = repository;
    }

    public List<Patient> listAllPatient(int page) {
        return repository.listAllPatient(page);
    }

}
