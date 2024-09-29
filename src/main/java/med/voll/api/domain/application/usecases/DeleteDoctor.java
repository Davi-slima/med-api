package med.voll.api.domain.application.usecases;

import med.voll.api.domain.application.gateway.DoctorRepository;

public class DeleteDoctor {

    private final DoctorRepository repository;

    public DeleteDoctor(DoctorRepository repository) {
        this.repository = repository;
    }

    public void deleteDoctor(String crm) {
        this.repository.deleteDoctor(crm);
    }

}
