package med.voll.api.domain.application.usecases.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatewayRepository;
import med.voll.api.domain.entities.Patient;

import java.util.List;

public class ListPatient {

    private final PatientGatewayRepository repository;

    public ListPatient(PatientGatewayRepository repository) {
        this.repository = repository;
    }

    public List<Patient> listAllPatient(int page) {
        return repository.listAllPatient(page);
    }

}
