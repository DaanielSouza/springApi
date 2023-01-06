package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorDto;
import med.voll.api.dto.PatientDataUpdate;
import med.voll.api.dto.PatientDto;
import med.voll.api.dto.PatientsDataList;
import med.voll.api.entity.PatientEntity;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PatientsController {

    @Autowired
    PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDto> addPatient(@RequestBody @Valid PatientDto p) {
        PatientEntity patient = new PatientEntity(p);
        repository.save(patient);

        if(patient.getId() > 0)
            return ResponseEntity.ok(p);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<PatientsDataList>> findPatients(@PageableDefault(size = 10, sort = {"nome", "email", "cpf"}) Pageable pagination) {

        var page = repository.findAll(pagination).map(PatientsDataList::new);

        if(page.getSize() > 0)
            return ResponseEntity.ok(page);
        else
            return  ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> findPatient(@PathVariable Long id){
        Optional<PatientEntity> patientOpt = repository.findOneByIdAndStatusTrue(id);
        return patientOpt.map(patientEntity -> ResponseEntity.ok(new PatientDto(patientEntity))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<PatientDto> updatePatient(PatientDataUpdate p) {
        Optional<PatientEntity> patient = repository.findById(p.id());
        if (patient.isPresent()) {
            PatientEntity entity = patient.get();
            entity.updateData(p);

            return ResponseEntity.ok(new PatientDto(entity));
        } else
            return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity<PatientDto> deletePatient(Long id) {
        Optional<PatientEntity> patient = repository.findById(id);
        if (patient.isPresent()) {
            PatientEntity finalPatient = patient.get();
            finalPatient.deactivate();
            return ResponseEntity.ok(new PatientDto(finalPatient));
        } else
            return ResponseEntity.notFound().build();
    }
}
