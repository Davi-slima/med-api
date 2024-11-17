package med.voll.api.infra.persistence.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    PatientEntity findByCpf(String cpf);
}
