package med.voll.api.config;

import med.voll.api.domain.application.gateway.DoctorRepository;
import med.voll.api.domain.application.usecases.CreateDoctor;
import med.voll.api.domain.application.usecases.ListDoctor;
import med.voll.api.infra.gateways.DoctorJpaRepository;
import med.voll.api.infra.gateways.DoctorMapper;
import med.voll.api.infra.persistence.MedicoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorConfig {

    @Bean
    CreateDoctor createDoctor(DoctorRepository repository) {
        return new CreateDoctor(repository);
    }

    @Bean
    DoctorJpaRepository doctorJpaRepository(MedicoRepository repository, DoctorMapper mapper){
        return new DoctorJpaRepository(repository, mapper);
    }

    @Bean
    DoctorMapper doctorMapper() {
        return new DoctorMapper();
    }

    @Bean
    ListDoctor listDoctor(DoctorRepository repository) {
        return new ListDoctor(repository);
    }

}
