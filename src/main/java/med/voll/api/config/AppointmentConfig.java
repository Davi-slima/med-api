package med.voll.api.config;

import med.voll.api.domain.application.gateway.appointment.AppointmentGatewayRepository;
import med.voll.api.domain.application.usecases.appointment.CreateAppointment;
import med.voll.api.domain.application.usecases.appointment.EndAppointment;
import med.voll.api.infra.gateways.Appointment.AppointmentJpaRepository;
import med.voll.api.infra.gateways.Appointment.AppointmentMapper;
import med.voll.api.infra.persistence.appointment.AppointmentRepository;
import med.voll.api.infra.persistence.doctor.DoctorRepository;
import med.voll.api.infra.persistence.patient.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentConfig {

    @Bean
    CreateAppointment createAppointment(AppointmentGatewayRepository repository) {
        return new CreateAppointment(repository);
    }

    @Bean
    EndAppointment endAppointment(AppointmentGatewayRepository repository) {
        return new EndAppointment(repository);
    }

    @Bean
    AppointmentGatewayRepository appointmentGatewayRepository(AppointmentRepository repository, AppointmentMapper mapper, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        return new AppointmentJpaRepository(repository, mapper, doctorRepository, patientRepository);
    }

    @Bean
    AppointmentMapper appointmentMapper() {
        return new AppointmentMapper();
    }

}
