package med.voll.api.infra.controller;

import med.voll.api.domain.application.usecases.CreateDoctor;
import med.voll.api.domain.entities.Doctor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class DoctorController {

    private final CreateDoctor createDoctor;

    public DoctorController(CreateDoctor createDoctor) {
        this.createDoctor = createDoctor;
    }

    @PostMapping
    DoctorDTO cadastrarMedico(@RequestBody DoctorDTO doctorDTO) {
        Doctor saveDoctor =createDoctor.createDoctor(
                new Doctor(doctorDTO.name(), doctorDTO.email(),
                        doctorDTO.crm(), doctorDTO.specialty(),
                        doctorDTO.address()));

        return new DoctorDTO(
                saveDoctor.getName(), saveDoctor.getEmail(),
                saveDoctor.getCrm(), saveDoctor.getSpecialty(),
                saveDoctor.getAddress());
    }

}
