package med.voll.api.infra.controller.appointment;

import med.voll.api.domain.application.usecases.appointment.CreateAppointment;
import med.voll.api.domain.application.usecases.appointment.EndAppointment;
import med.voll.api.domain.entities.Appointment;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private final CreateAppointment createAppointment;
    private final EndAppointment endAppointment;

    public AppointmentController(CreateAppointment createAppointment, EndAppointment endAppointment) {
        this.createAppointment = createAppointment;
        this.endAppointment = endAppointment;
    }

    @PostMapping
    public AppointmentDTO createScheduling(@RequestBody AppointmentRequestDTO request) throws ChangeSetPersister.NotFoundException {
        Appointment appointment = createAppointment.CreateAppointment(new Appointment(null, request.doctorId(),
                request.patientId(), request.dateTime(), null));

        return new AppointmentDTO(appointment.getAppointmentId(),
                appointment.getDoctorId(), appointment.getPatientId(),
                appointment.getDateTime(), appointment.getStatus());
    }

    @PatchMapping("/{id}/finalize")
    public ResponseEntity<Void> finalizeAppointment(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        endAppointment.endAppointment(new Appointment(id, null, null, null, null));

        return ResponseEntity.noContent().build();
    }


}
