package med.voll.api.domain.application.gateway.doctor;

import med.voll.api.domain.entities.Doctor;

import java.util.List;

public interface DoctorGatwayRepository {

    Doctor createDoctor(Doctor doctor);

    List<Doctor> listAllDoctors(int page);

    Doctor updateDoctor(Doctor doctor);

    void deleteDoctor(String crm);

}
