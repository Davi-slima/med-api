package med.voll.api.domain.application.usecases.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatwayRepository;
import med.voll.api.domain.entities.Patient;

public class UpdatePatient {

    private final PatientGatwayRepository repository;

    public UpdatePatient(PatientGatwayRepository repository) {
        this.repository = repository;
    }

    public Patient updatePatient(Patient patient) {
        return this.repository.updatePatient(patient);
    }

}