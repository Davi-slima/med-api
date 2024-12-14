package med.voll.api.infra.gateways.Appointment;

import med.voll.api.domain.entities.Appointment;
import med.voll.api.infra.persistence.appointment.AppointmentEntity;
import med.voll.api.infra.persistence.doctor.DoctorEntity;
import med.voll.api.infra.persistence.patient.PatientEntity;

public class AppointmentMapper {


    AppointmentEntity toEntity(Appointment appointment, DoctorEntity doctorEntity, PatientEntity patientEntity) {
        return new AppointmentEntity(doctorEntity, patientEntity,
                appointment.getDateTime(), appointment.getStatus());
    }

    Appointment toDomain(AppointmentEntity entity) {
        return new Appointment(entity.getAppointmentId(), entity.getDoctor().getId(),
                entity.getPatient().getId(), entity.getDateTime(), entity.getStatus());
    }
}
