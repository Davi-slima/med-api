package med.voll.api.config;

import med.voll.api.domain.application.gateway.doctor.DoctorGatwayRepository;
import med.voll.api.domain.application.usecases.doctor.CreateDoctor;
import med.voll.api.domain.application.usecases.doctor.DeleteDoctor;
import med.voll.api.domain.application.usecases.doctor.ListDoctor;
import med.voll.api.domain.application.usecases.doctor.UpdateDoctor;
import med.voll.api.infra.gateways.doctor.DoctorJpaRepository;
import med.voll.api.infra.gateways.doctor.DoctorMapper;
import med.voll.api.infra.persistence.doctor.DoctorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorConfig {

    @Bean
    CreateDoctor createDoctor(DoctorGatwayRepository repository) {
        return new CreateDoctor(repository);
    }

    @Bean
    DoctorJpaRepository doctorJpaRepository(DoctorRepository repository, DoctorMapper mapper) {
        return new DoctorJpaRepository(repository, mapper);
    }

    @Bean
    DoctorMapper doctorMapper() {
        return new DoctorMapper();
    }

    @Bean
    ListDoctor listDoctor(DoctorGatwayRepository repository) {
        return new ListDoctor(repository);
    }

    @Bean
    UpdateDoctor updateDoctor(DoctorGatwayRepository repository) {
        return new UpdateDoctor(repository);
    }

    @Bean
    DeleteDoctor deleteDoctor(DoctorGatwayRepository repository) {
        return new DeleteDoctor(repository);
    }
}
