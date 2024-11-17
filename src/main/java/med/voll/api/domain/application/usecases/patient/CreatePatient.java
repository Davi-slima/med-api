package med.voll.api.domain.application.usecases.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatwayRepository;
import med.voll.api.domain.entities.Patient;

public class CreatePatient {

    private final PatientGatwayRepository repository;

    public CreatePatient(PatientGatwayRepository repository) {
        this.repository = repository;
    }

    public Patient createPatient(Patient patient) {
        return this.repository.createPatient(patient);
    }
}
