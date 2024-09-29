package med.voll.api.infra.controller;

import med.voll.api.domain.application.usecases.CreateDoctor;
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

    public DoctorController(CreateDoctor createDoctor, ListDoctor listDoctor, UpdateDoctor updateDoctor) {
        this.createDoctor = createDoctor;
        this.listDoctor = listDoctor;
        this.updateDoctor = updateDoctor;
    }

    @PostMapping
    public DoctorDTO cadastrarMedico(@RequestBody DoctorDTO doctorDTO) {
        Doctor saveDoctor = createDoctor.createDoctor(
                new Doctor(doctorDTO.name(), doctorDTO.email(), doctorDTO.phoneNumber(),
                        doctorDTO.crm(), doctorDTO.specialty(), doctorDTO.address()));

        return new DoctorDTO(
                saveDoctor.getName(), saveDoctor.getEmail(),
                saveDoctor.getPhoneNumber(),
                saveDoctor.getCrm(), saveDoctor.getSpecialty(),
                saveDoctor.getAddress());
    }

    @GetMapping
    public DoctorDTOResponse listAllDoctors(@RequestParam(defaultValue = "0") int page) {
        return DoctorDTOResponse.builder()
                .content(listDoctor.listAllDoctors(page).stream()
                        .map(doctor -> new DoctorDTO(doctor.getName(), doctor.getEmail(), doctor.getPhoneNumber(),
                                doctor.getCrm(), doctor.getSpecialty(), null))
                        .toList())
                .build();

    }

    @PutMapping("/{crm}")
    public DoctorDTO updateDoctor(@PathVariable String crm, @RequestBody UpdateDTORequest updateDTORequest) {
        Doctor doctor = updateDoctor.UpdateDoctor(
                new Doctor(updateDTORequest.name(), null, updateDTORequest.phoneNumber(),
                        crm, null, updateDTORequest.address()));
        return new DoctorDTO(doctor.getName(), doctor.getEmail(), doctor.getPhoneNumber(),
                doctor.getCrm(), doctor.getSpecialty(),
                doctor.getAddress());
    }

}
