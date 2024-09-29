package med.voll.api.domain.application.usecases;

import med.voll.api.domain.application.gateway.DoctorRepository;
import med.voll.api.domain.entities.Doctor;

public class UpdateDoctor {

    private final DoctorRepository repository;

    public UpdateDoctor(DoctorRepository repository) {
        this.repository = repository;
    }

    public Doctor UpdateDoctor(Doctor doctor) {
        return this.repository.UpdateDoctor(doctor);
    }
}
