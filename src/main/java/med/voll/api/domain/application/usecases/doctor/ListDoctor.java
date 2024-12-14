package med.voll.api.domain.application.usecases.doctor;

import med.voll.api.domain.application.gateway.doctor.DoctorGatewayRepository;
import med.voll.api.domain.entities.Doctor;

import java.util.List;

public class ListDoctor {

    private final DoctorGatewayRepository repository;

    public ListDoctor(DoctorGatewayRepository repository) {
        this.repository = repository;
    }

    public List<Doctor> listAllDoctors(int page) {
        return this.repository.listAllDoctors(page);
    }

}
