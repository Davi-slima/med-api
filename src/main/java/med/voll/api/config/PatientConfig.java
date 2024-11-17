package med.voll.api.config;

import med.voll.api.domain.application.gateway.patient.PatientGatwayRepository;
import med.voll.api.domain.application.usecases.patient.CreatePatient;
import med.voll.api.infra.gateways.patient.PatientJpaRepository;
import med.voll.api.infra.gateways.patient.PatientMapper;
import med.voll.api.infra.persistence.patient.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientConfig {

    @Bean
    CreatePatient createPatient(PatientGatwayRepository repository) {
        return new CreatePatient(repository);
    }

    @Bean
    PatientJpaRepository patientJpaRepository(PatientRepository repository, PatientMapper mapper) {
        return new PatientJpaRepository(repository, mapper);
    }

    @Bean
    PatientMapper patientMapper() {
        return new PatientMapper();
    }

}