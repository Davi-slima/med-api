package med.voll.api.config;

import med.voll.api.domain.application.gateway.doctor.DoctorGatewayRepository;
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
    CreateDoctor createDoctor(DoctorGatewayRepository repository) {
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
    ListDoctor listDoctor(DoctorGatewayRepository repository) {
        return new ListDoctor(repository);
    }

    @Bean
    UpdateDoctor updateDoctor(DoctorGatewayRepository repository) {
        return new UpdateDoctor(repository);
    }

    @Bean
    DeleteDoctor deleteDoctor(DoctorGatewayRepository repository) {
        return new DeleteDoctor(repository);
    }
}
