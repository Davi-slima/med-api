package med.voll.api.domain.application.usecases.appointment;

import med.voll.api.domain.application.gateway.appointment.AppointmentGatewayRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

public class CancelAppointment {

    private final AppointmentGatewayRepository repository;

    public CancelAppointment(AppointmentGatewayRepository repository) {
        this.repository = repository;
    }

    public void cancelAppointment(Long id) throws ChangeSetPersister.NotFoundException {
        this.repository.cancelAppointment(id);
    }

}
