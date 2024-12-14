package med.voll.api.infra.controller.patient;


import med.voll.api.domain.application.usecases.patient.CreatePatient;
import med.voll.api.domain.application.usecases.patient.DeletePatient;
import med.voll.api.domain.application.usecases.patient.ListPatient;
import med.voll.api.domain.application.usecases.patient.UpdatePatient;
import med.voll.api.domain.entities.Patient;
import med.voll.api.infra.controller.UpdateDTORequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
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
    public ResponseEntity<Void> cadastroPaciente(@RequestBody PatientDTO dto) {
        createPatient.createPatient(new Patient(null, dto.name(),
                dto.email(), dto.phoneNumber(), dto.cpf(), true, dto.address()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public Page<PatientDTO> listarPacientes(@RequestParam(defaultValue = "0") int page) {
        List<PatientDTO> patientDTOList = listPatient.listAllPatient(page).stream().map(patient -> new PatientDTO(patient.getId() ,patient.getName(), patient.getEmail(),
                patient.getPhoneNumber(), patient.getCpf(), null, patient.isActive())).toList();

        return new PageImpl<>(patientDTOList, PageRequest.of(page, 10), listPatient.listAllPatient(page).size());

    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Void> atualizaCadastro(@PathVariable String cpf, @RequestBody UpdateDTORequest request) {
        updatePatient.updatePatient(new Patient(null, request.name(), null,
                request.phoneNumber(), cpf, true, request.address()));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{cpf}")
    public void removePaciente(@PathVariable String cpf) {
        deletePatient.deletePatient(cpf);
    }

}