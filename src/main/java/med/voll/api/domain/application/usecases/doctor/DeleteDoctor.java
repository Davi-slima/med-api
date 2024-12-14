package med.voll.api.domain.application.usecases.doctor;

import med.voll.api.domain.application.gateway.doctor.DoctorGatewayRepository;

public class DeleteDoctor {

    private final DoctorGatewayRepository repository;

    public DeleteDoctor(DoctorGatewayRepository repository) {
        this.repository = repository;
    }

    public void deleteDoctor(String crm) {
        this.repository.deleteDoctor(crm);
    }

}
