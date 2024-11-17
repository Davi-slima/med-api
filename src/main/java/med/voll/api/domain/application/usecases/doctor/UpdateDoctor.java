package med.voll.api.domain.application.usecases.doctor;

import med.voll.api.domain.application.gateway.doctor.DoctorGatwayRepository;
import med.voll.api.domain.entities.Doctor;

public class UpdateDoctor {

    private final DoctorGatwayRepository repository;

    public UpdateDoctor(DoctorGatwayRepository repository) {
        this.repository = repository;
    }

    public Doctor updateDoctor(Doctor doctor) {
        return this.repository.updateDoctor(doctor);
    }
}
