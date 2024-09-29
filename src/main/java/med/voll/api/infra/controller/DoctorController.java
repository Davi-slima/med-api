package med.voll.api.infra.controller;

import med.voll.api.domain.application.usecases.CreateDoctor;
import med.voll.api.domain.application.usecases.DeleteDoctor;
import med.voll.api.domain.application.usecases.ListDoctor;
import med.voll.api.domain.application.usecases.UpdateDoctor;
import med.voll.api.domain.entities.Doctor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
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
    public DoctorDTO cadastrarMedico(@RequestBody DoctorDTO doctorDTO) {
        Doctor saveDoctor = createDoctor.createDoctor(
                new Doctor(doctorDTO.name(), doctorDTO.email(), doctorDTO.phoneNumber(),
                        doctorDTO.crm(), doctorDTO.specialty(), doctorDTO.address(), true));

        return new DoctorDTO(
                saveDoctor.getName(), saveDoctor.getEmail(),
                saveDoctor.getPhoneNumber(),
                saveDoctor.getCrm(), saveDoctor.getSpecialty(),
                saveDoctor.getAddress(), saveDoctor.isActive());
    }

    @GetMapping
    public DoctorDTOResponse listAllDoctors(@RequestParam(defaultValue = "0") int page) {
        return DoctorDTOResponse.builder()
                .content(listDoctor.listAllDoctors(page).stream()
                        .map(doctor -> new DoctorDTO(doctor.getName(), doctor.getEmail(), doctor.getPhoneNumber(),
                                doctor.getCrm(), doctor.getSpecialty(), null, doctor.isActive()))
                        .toList())
                .build();

    }

    @PutMapping("/{crm}")
    public DoctorDTO updateDoctor(@PathVariable String crm, @RequestBody UpdateDTORequest updateDTORequest) {
        Doctor doctor = updateDoctor.UpdateDoctor(
                new Doctor(updateDTORequest.name(), null, updateDTORequest.phoneNumber(),
                        crm, null, updateDTORequest.address(), true));
        return new DoctorDTO(doctor.getName(), doctor.getEmail(), doctor.getPhoneNumber(),
                doctor.getCrm(), doctor.getSpecialty(),
                doctor.getAddress(), doctor.isActive());
    }

    @DeleteMapping("/{crm}")
    public void deleteDoctor(@PathVariable String crm) {
        deleteDoctor.deleteDoctor(crm);
    }
}
