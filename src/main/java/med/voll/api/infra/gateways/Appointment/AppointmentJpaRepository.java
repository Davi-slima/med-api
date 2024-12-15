package med.voll.api.infra.gateways.Appointment;

import med.voll.api.domain.application.gateway.appointment.AppointmentGatewayRepository;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.enums.Status;
import med.voll.api.infra.persistence.appointment.AppointmentEntity;
import med.voll.api.infra.persistence.appointment.AppointmentRepository;
import med.voll.api.infra.persistence.doctor.DoctorEntity;
import med.voll.api.infra.persistence.doctor.DoctorRepository;
import med.voll.api.infra.persistence.patient.PatientEntity;
import med.voll.api.infra.persistence.patient.PatientRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Comparator;
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
        appointment.setStatus(Status.SCHEDULED);

        DoctorEntity doctorEntity = findDoctorEntity(appointment);
        PatientEntity patientEntity = findPatientEntity(appointment);

        AppointmentEntity entity = mapper.toEntity(appointment, doctorEntity, patientEntity);
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public void endAppointment(Appointment appointment) throws ChangeSetPersister.NotFoundException {
        AppointmentEntity appointmentEntity = repository.findById(appointment.getAppointmentId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (!appointmentEntity.getStatus().equals(Status.SCHEDULED)) {
            throw new IllegalArgumentException("Only scheduled appointments can be finalized.");
        }
        appointmentEntity.setStatus(Status.COMPLETED);

        repository.save(appointmentEntity);
    }

    @Override
    public List<Appointment> listAllAppointment(int page) {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(AppointmentEntity::getDateTime))
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void cancelAppointment(Long id) throws ChangeSetPersister.NotFoundException {
        AppointmentEntity entity = repository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        entity.setStatus(Status.CANCELLED);
        repository.save(entity);
        mapper.toDomain(entity);

    }

    private DoctorEntity findDoctorEntity(Appointment appointment) throws ChangeSetPersister.NotFoundException {
        return doctorRepository.findById(appointment.getDoctorId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    private PatientEntity findPatientEntity(Appointment appointment) throws ChangeSetPersister.NotFoundException {
        return patientRepository.findById(appointment.getPatientId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
