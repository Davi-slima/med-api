package med.voll.api.infra.controller;

import med.voll.api.domain.application.usecases.CreateDoctor;
import med.voll.api.domain.application.usecases.ListDoctor;
import med.voll.api.domain.entities.Doctor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class DoctorController {

    private final CreateDoctor createDoctor;
    private final ListDoctor listDoctor;

    public DoctorController(CreateDoctor createDoctor, ListDoctor listDoctor) {
        this.createDoctor = createDoctor;
        this.listDoctor = listDoctor;
    }

    @PostMapping
    public DoctorDTO cadastrarMedico(@RequestBody DoctorDTO doctorDTO) {
        Doctor saveDoctor = createDoctor.createDoctor(
                new Doctor(doctorDTO.name(), doctorDTO.email(),
                        doctorDTO.crm(), doctorDTO.specialty(),
                        doctorDTO.address()));

        return new DoctorDTO(
                saveDoctor.getName(), saveDoctor.getEmail(),
                saveDoctor.getCrm(), saveDoctor.getSpecialty(),
                saveDoctor.getAddress());
    }

    @GetMapping
    public DoctorDTOResponse listAllDoctors(@RequestParam(defaultValue = "0") int page) {
        return DoctorDTOResponse.builder()
                .content(listDoctor.listAllDoctors(page).stream()
                        .map(doctor -> new DoctorDTO(doctor.getName(), doctor.getEmail(),
                                doctor.getCrm(), doctor.getSpecialty(), null))
                        .toList())
                .build();

    }

}
