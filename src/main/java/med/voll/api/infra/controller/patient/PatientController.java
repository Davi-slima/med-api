package med.voll.api.infra.controller.patient;


import med.voll.api.domain.application.usecases.patient.CreatePatient;
import med.voll.api.domain.application.usecases.patient.ListPatient;
import med.voll.api.domain.entities.Patient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PatientController {

    private final CreatePatient createPatient;
    private final ListPatient listPatient;

    public PatientController(CreatePatient createPatient, ListPatient listPatient) {
        this.createPatient = createPatient;
        this.listPatient = listPatient;
    }

    @PostMapping
    public PatientDTO cadastroPaciente(@RequestBody PatientDTO dto) {
        Patient patient = createPatient.createPatient(new Patient(dto.name(),
                dto.email(), dto.phoneNumber(), dto.cpf(), dto.address()));

        return new PatientDTO(patient.getName(), patient.getEmail(),
                dto.phoneNumber(), patient.getCpf(), patient.getAddress());
    }

    @GetMapping
    public PatientDTOResponse listarPacientes(@RequestParam(defaultValue = "0") int page) {
        return PatientDTOResponse.builder()
                .content(listPatient.listAllPatient(page)
                        .stream()
                        .map(patient -> new PatientDTO(patient.getName(), patient.getEmail(),
                                patient.getPhoneNumber(), patient.getCpf(), patient.getAddress()))
                        .toList())
                .build();
    }

}
