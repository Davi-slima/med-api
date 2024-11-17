package med.voll.api.infra.controller.patient;


import med.voll.api.domain.application.usecases.patient.CreatePatient;
import med.voll.api.domain.entities.Patient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PatientController {

    private final CreatePatient createPatient;

    public PatientController(CreatePatient createPatient) {
        this.createPatient = createPatient;
    }

    @PostMapping
    public PatientDTO cadastroPaciente(@RequestBody PatientDTO dto) {
        Patient patient = createPatient.createPatient(new Patient(dto.name(),
                dto.email(), dto.phoneNumber(), dto.cpf(), dto.address()));

        return new PatientDTO(patient.getName(), patient.getEmail(),
                dto.phoneNumber(), patient.getCpf(), patient.getAddress());
    }


}
