package med.voll.api.infra.controller.appointment;

import med.voll.api.domain.application.usecases.appointment.CreateAppointment;
import med.voll.api.domain.application.usecases.appointment.EndAppointment;
import med.voll.api.domain.application.usecases.appointment.ListAllApointments;
import med.voll.api.domain.entities.Appointment;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private final CreateAppointment createAppointment;
    private final EndAppointment endAppointment;
    private final ListAllApointments listAllApointments;

    public AppointmentController(CreateAppointment createAppointment, EndAppointment endAppointment, ListAllApointments listAllApointments) {
        this.createAppointment = createAppointment;
        this.endAppointment = endAppointment;
        this.listAllApointments = listAllApointments;
    }

    @PostMapping
    public AppointmentDTO createScheduling(@RequestBody AppointmentRequestDTO request) throws ChangeSetPersister.NotFoundException {
        Appointment appointment = createAppointment.CreateAppointment(new Appointment(null, request.doctorId(),
                request.patientId(), request.dateTime(), null));

        return new AppointmentDTO(appointment.getAppointmentId(),
                appointment.getDoctorId(), appointment.getPatientId(),
                appointment.getDateTime(), appointment.getStatus());
    }

    @GetMapping
    public Page<AppointmentDTO> getAppointments(@RequestParam(defaultValue = "0") int page) {
        List<AppointmentDTO> appointments = listAllApointments.listAllApointments(page)
                .stream().map(appointment -> new AppointmentDTO(appointment.getAppointmentId(), appointment.getDoctorId(),
                        appointment.getPatientId(), appointment.getDateTime(), appointment.getStatus())).toList();
        return new PageImpl<>(appointments, PageRequest.of(page, 10), appointments.size());
    }

    @PatchMapping("/{id}/finalize")
    public ResponseEntity<Void> finalizeAppointment(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        endAppointment.endAppointment(new Appointment(id, null, null, null, null));

        return ResponseEntity.noContent().build();
    }


}
