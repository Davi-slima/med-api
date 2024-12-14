package med.voll.api.domain.application.gateway.appointment;

import med.voll.api.domain.entities.Appointment;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface AppointmentGatewayRepository {

    Appointment createAppointment(Appointment appointment) throws ChangeSetPersister.NotFoundException;
    Appointment endAppointment (Appointment appointment);
    List<Appointment> listAllAppointment(int page);
    void cancelAppointment(Long id);

}
