package med.voll.api.domain.application.usecases.appointment;

import med.voll.api.domain.application.gateway.appointment.AppointmentGatewayRepository;
import med.voll.api.domain.entities.Appointment;
import org.springframework.data.crossstore.ChangeSetPersister;

public class CreateAppointment {

    private final AppointmentGatewayRepository repository;

    public CreateAppointment(AppointmentGatewayRepository repository) {
        this.repository = repository;
    }

    public Appointment CreateAppointment(Appointment appointment) throws ChangeSetPersister.NotFoundException {
        return this.repository.createAppointment(appointment);
    }

}
