package med.voll.api.domain.application.usecases.doctor;

import med.voll.api.domain.application.gateway.doctor.DoctorGatewayRepository;
import med.voll.api.domain.entities.Doctor;

public class UpdateDoctor {

    private final DoctorGatewayRepository repository;

    public UpdateDoctor(DoctorGatewayRepository repository) {
        this.repository = repository;
    }

    public Doctor updateDoctor(Doctor doctor) {
        return this.repository.updateDoctor(doctor);
    }
}
