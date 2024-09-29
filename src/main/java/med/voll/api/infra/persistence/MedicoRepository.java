package med.voll.api.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<DoctorEntity, Long> {

    DoctorEntity findByCrm(String crm);
}
