package med.voll.api.domain.application.usecases.patient;

import med.voll.api.domain.application.gateway.patient.PatientGatewayRepository;

public class DeletePatient {

    private final PatientGatewayRepository repository;

    public DeletePatient(PatientGatewayRepository repository) {
        this.repository = repository;
    }

    public void  deletePatient(String cpf) {
        this.repository.deletePatient(cpf);
    }

}
