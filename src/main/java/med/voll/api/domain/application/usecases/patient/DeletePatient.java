package med.voll.api.domain.application.usecases.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatwayRepository;

public class DeletePatient {

    private final PatientGatwayRepository repository;

    public DeletePatient(PatientGatwayRepository repository) {
        this.repository = repository;
    }

    public void  deletePatient(String cpf) {
        this.repository.deletePatient(cpf);
    }

}
