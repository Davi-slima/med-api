package med.voll.api.domain.application.usecases.doctor;

import med.voll.api.domain.application.gateway.doctor.DoctorGatwayRepository;

public class DeleteDoctor {

    private final DoctorGatwayRepository repository;

    public DeleteDoctor(DoctorGatwayRepository repository) {
        this.repository = repository;
    }

    public void deleteDoctor(String crm) {
        this.repository.deleteDoctor(crm);
    }

}
