package med.voll.api.domain.application.gateway;

import med.voll.api.domain.entities.Doctor;

import java.util.List;

public interface DoctorRepository {

    Doctor createDoctor(Doctor doctor);

    List<Doctor> ListAllDoctors(int page);

    Doctor UpdateDoctor(String crm, Doctor doctor);

    void deleteDoctor(String crm);

}
