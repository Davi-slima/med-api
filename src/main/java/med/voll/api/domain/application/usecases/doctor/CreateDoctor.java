package med.voll.api.domain.application.usecases.doctor;

import med.voll.api.domain.application.gateway.doctor.DoctorGatwayRepository;
import med.voll.api.domain.entities.Doctor;

public class CreateDoctor {

    private final DoctorGatwayRepository repository;

    public CreateDoctor(DoctorGatwayRepository doctorGatwayRepository) {
        this.repository = doctorGatwayRepository;
    }

    public Doctor createDoctor(Doctor doctor) {
        return this.repository.createDoctor(doctor);
    }

}
