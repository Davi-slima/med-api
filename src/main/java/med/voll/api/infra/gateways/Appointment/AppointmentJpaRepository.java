package med.voll.api.infra.gateways.Appointment;

import med.voll.api.domain.application.gateway.appointment.AppointmentGatewayRepository;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.Patient;
import med.voll.api.enums.Status;
import med.voll.api.infra.persistence.appointment.AppointmentEntity;
import med.voll.api.infra.persistence.appointment.AppointmentRepository;
import med.voll.api.infra.persistence.doctor.DoctorEntity;
import med.voll.api.infra.persistence.doctor.DoctorRepository;
import med.voll.api.infra.persistence.patient.PatientEntity;
import med.voll.api.infra.persistence.patient.PatientRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public class AppointmentJpaRepository implements AppointmentGatewayRepository {

    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentJpaRepository(AppointmentRepository repository, AppointmentMapper mapper, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) throws ChangeSetPersister.NotFoundException {

        DoctorEntity doctorEntity = doctorRepository.findById(appointment.getDoctorId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        PatientEntity patientEntity = patientRepository.findById(appointment.getPatientId())
                        .orElseThrow(ChangeSetPersister.NotFoundException::new);

        appointment.setDoctorId(doctorEntity.getId());
        appointment.setPatientId(patientEntity.getId());
        appointment.setStaus(Status.SCHEDULED);

        AppointmentEntity entity = mapper.toEntity(appointment, doctorEntity, patientEntity);
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Appointment endAppointment(Appointment appointment) {
        return null;
    }

    @Override
    public List<Appointment> listAllAppointment(int page) {
        return null;
    }

    @Override
    public void cancelAppointment(Long id) {

    }
}
