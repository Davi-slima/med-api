package med.voll.api.domain.application.usecases.doctor;

import med.voll.api.domain.application.gateway.doctor.DoctorGatewayRepository;
import med.voll.api.domain.entities.Doctor;

public class CreateDoctor {

    private final DoctorGatewayRepository repository;

    public CreateDoctor(DoctorGatewayRepository doctorGatewayRepository) {
        this.repository = doctorGatewayRepository;
    }

    public Doctor createDoctor(Doctor doctor) {
        return this.repository.createDoctor(doctor);
    }

}
