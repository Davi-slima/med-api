package med.voll.api.domain.application.usecases.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatewayRepository;
import med.voll.api.domain.entities.Patient;

public class UpdatePatient {

    private final PatientGatewayRepository repository;

    public UpdatePatient(PatientGatewayRepository repository) {
        this.repository = repository;
    }

    public Patient updatePatient(Patient patient) {
        return this.repository.updatePatient(patient);
    }

}