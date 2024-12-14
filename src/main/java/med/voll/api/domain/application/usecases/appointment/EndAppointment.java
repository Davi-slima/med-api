package med.voll.api.domain.application.usecases.appointment;

import med.voll.api.domain.application.gateway.appointment.AppointmentGatewayRepository;
import med.voll.api.domain.entities.Appointment;
import org.springframework.data.crossstore.ChangeSetPersister;

public class EndAppointment {

    private final AppointmentGatewayRepository repository;

    public EndAppointment(AppointmentGatewayRepository repository) {
        this.repository = repository;
    }

    public void endAppointment(Appointment appointment) throws ChangeSetPersister.NotFoundException {
        this.repository.endAppointment(appointment);
    }
}