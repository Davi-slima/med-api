package med.voll.api.domain.application.usecases.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatewayRepository;
import med.voll.api.domain.entities.Patient;

public class CreatePatient {

    private final PatientGatewayRepository repository;

    public CreatePatient(PatientGatewayRepository repository) {
        this.repository = repository;
    }

    public Patient createPatient(Patient patient) {
        return this.repository.createPatient(patient);
    }
}
