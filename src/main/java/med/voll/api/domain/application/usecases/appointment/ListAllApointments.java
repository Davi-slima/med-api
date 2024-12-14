package med.voll.api.domain.application.usecases.appointment;

import med.voll.api.domain.application.gateway.appointment.AppointmentGatewayRepository;
import med.voll.api.domain.entities.Appointment;

import java.util.List;

public class ListAllApointments {

    private final AppointmentGatewayRepository repository;

    public ListAllApointments(AppointmentGatewayRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> listAllApointments(int page) {
        return this.repository.listAllAppointment(page);
    }

}
