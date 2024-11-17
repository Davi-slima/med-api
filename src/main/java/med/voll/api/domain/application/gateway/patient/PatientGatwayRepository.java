package med.voll.api.domain.application.gateway.patient;

import med.voll.api.domain.entities.Patient;

import java.util.List;

public interface PatientGatwayRepository {

    Patient createPatient(Patient patient);
    List<Patient> listAllPatient(int page);
    Patient updatePatient(Patient patient);
    void deletePatient(String cpf);
}
