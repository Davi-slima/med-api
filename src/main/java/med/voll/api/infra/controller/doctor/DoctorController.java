package med.voll.api.infra.controller.doctor;

import med.voll.api.domain.application.usecases.doctor.CreateDoctor;
import med.voll.api.domain.application.usecases.doctor.DeleteDoctor;
import med.voll.api.domain.application.usecases.doctor.ListDoctor;
import med.voll.api.domain.application.usecases.doctor.UpdateDoctor;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.infra.controller.UpdateDTORequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    private final CreateDoctor createDoctor;
    private final ListDoctor listDoctor;
    private final UpdateDoctor updateDoctor;
    private final DeleteDoctor deleteDoctor;

    public DoctorController(CreateDoctor createDoctor, ListDoctor listDoctor, UpdateDoctor updateDoctor, DeleteDoctor deleteDoctor) {
        this.createDoctor = createDoctor;
        this.listDoctor = listDoctor;
        this.updateDoctor = updateDoctor;
        this.deleteDoctor = deleteDoctor;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarMedico(@RequestBody DoctorDTO doctorDTO) {
        createDoctor.createDoctor(
                new Doctor(null, doctorDTO.name(), doctorDTO.email(), doctorDTO.phoneNumber(),
                        doctorDTO.crm(), doctorDTO.specialty(), doctorDTO.address(), true));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public Page<DoctorDTO> listAllDoctors(@RequestParam(defaultValue = "0") int page) {
        List<DoctorDTO> doctors = listDoctor.listAllDoctors(page).stream()
                .map(doctor -> new DoctorDTO(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhoneNumber(),
                        doctor.getCrm(), doctor.getSpecialty(), null, doctor.isActive()))
                .toList();
        return new PageImpl<>(doctors, PageRequest.of(page, 10), doctors.size());
    }

    @PutMapping("/{crm}")
    public ResponseEntity<Void> updateDoctor(@PathVariable String crm, @RequestBody UpdateDTORequest updateDTORequest) {
        updateDoctor.updateDoctor(
                new Doctor(updateDTORequest.id(), updateDTORequest.name(), null, updateDTORequest.phoneNumber(),
                        crm, null, updateDTORequest.address(), true));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{crm}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String crm) {
        deleteDoctor.deleteDoctor(crm);
        return ResponseEntity.noContent().build();
    }
}
