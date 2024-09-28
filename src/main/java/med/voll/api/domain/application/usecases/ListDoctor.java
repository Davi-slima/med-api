package med.voll.api.domain.application.usecases;

import med.voll.api.domain.application.gateway.DoctorRepository;
import med.voll.api.domain.entities.Doctor;

import java.util.List;

public class ListDoctor {

    private final DoctorRepository repository;

    public ListDoctor(DoctorRepository repository) {
        this.repository = repository;
    }

    public List<Doctor> listAllDoctors(int page) {
        return this.repository.ListAllDoctors(page);
    }

}
