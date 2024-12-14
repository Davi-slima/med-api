package med.voll.api.infra.controller.appointment;

import med.voll.api.domain.application.usecases.appointment.CreateAppointment;
import med.voll.api.domain.entities.Appointment;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private final CreateAppointment createAppointment;

    public AppointmentController(CreateAppointment createAppointment) {
        this.createAppointment = createAppointment;
    }

    @PostMapping
    public AppointmentDTO createScheduling(@RequestBody AppointmentRequestDTO request) throws ChangeSetPersister.NotFoundException {
        Appointment appointment = createAppointment.CreateAppointment(new Appointment(null, request.doctorId(),
                request.patientId(), request.dateTime(), null));

        return new AppointmentDTO(appointment.getAppointmentId(),
                appointment.getDoctorId(), appointment.getPatientId(),
                appointment.getDateTime(), appointment.getStaus());
    }


}
