package med.voll.api.infra.controller.patient;


import med.voll.api.domain.application.usecases.patient.CreatePatient;
import med.voll.api.domain.application.usecases.patient.DeletePatient;
import med.voll.api.domain.application.usecases.patient.ListPatient;
import med.voll.api.domain.application.usecases.patient.UpdatePatient;
import med.voll.api.domain.entities.Patient;
import med.voll.api.infra.controller.UpdateDTORequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PatientController {

    private final CreatePatient createPatient;
    private final ListPatient listPatient;
    private final UpdatePatient updatePatient;
    private final DeletePatient deletePatient;

    public PatientController(CreatePatient createPatient, ListPatient listPatient, UpdatePatient updatePatient, DeletePatient deletePatient) {
        this.createPatient = createPatient;
        this.listPatient = listPatient;
        this.updatePatient = updatePatient;
        this.deletePatient = deletePatient;
    }

    @PostMapping
    public PatientDTO cadastroPaciente(@RequestBody PatientDTO dto) {
        Patient patient = createPatient.createPatient(new Patient(dto.name(),
                dto.email(), dto.phoneNumber(), dto.cpf(), true, dto.address()));

        return new PatientDTO(patient.getName(), patient.getEmail(),
                dto.phoneNumber(), patient.getCpf(), patient.getAddress(), patient.isActive());
    }

    @GetMapping
    public PatientDTOResponse listarPacientes(@RequestParam(defaultValue = "0") int page) {
        return PatientDTOResponse.builder()
                .content(listPatient.listAllPatient(page)
                        .stream()
                        .map(patient -> new PatientDTO(patient.getName(), patient.getEmail(),
                                patient.getPhoneNumber(), patient.getCpf(), patient.getAddress(), patient.isActive()))
                        .toList())
                .build();
    }

    @PutMapping("/{cpf}")
    public PatientDTO atualizaCadastro(@PathVariable String cpf, @RequestBody UpdateDTORequest request) {
        Patient patient = updatePatient.updatePatient(new Patient(request.name(), null,
                request.phoneNumber(), cpf, true, request.address()));

        return new PatientDTO(patient.getName(), patient.getEmail(), patient.getPhoneNumber(),
                patient.getCpf(), patient.getAddress(), patient.isActive());
    }

    @DeleteMapping("/{cpf}")
    public void removePaciente(@PathVariable String cpf) {
        deletePatient.deletePatient(cpf);
    }

}