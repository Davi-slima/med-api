package med.voll.api.infra.persistence.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    DoctorEntity findByCrm(String crm);
}
