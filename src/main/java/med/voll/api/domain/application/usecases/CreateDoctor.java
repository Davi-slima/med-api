package med.voll.api.domain.application.usecases;

import med.voll.api.domain.application.gateway.DoctorRepository;
import med.voll.api.domain.entities.Doctor;

public class CreateDoctor {

    private final DoctorRepository repository;

    public CreateDoctor(DoctorRepository doctorRepository) {
        this.repository = doctorRepository;
    }

    public Doctor createDoctor(Doctor doctor) {
        return this.repository.createDoctor(doctor);
    }

}
