package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.PatientDataUpdate;
import med.voll.api.dto.PatientDto;
import med.voll.api.dto.PatientsDataList;
import med.voll.api.entity.PatientEntity;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public void addPatient(@RequestBody @Valid PatientDto patient){
        repository.save(new PatientEntity(patient));
    }

    @GetMapping
    public Page<PatientsDataList> findPatients(@PageableDefault(size = 10, sort = {"nome","email","cpf"}) Pageable pagination){
        return repository.findAll(pagination).map(PatientsDataList::new);
    }

    @PutMapping
    public void updatePatient(PatientDataUpdate p){
        Optional<PatientEntity> patient = repository.findById(p.id());
        if(patient.isPresent()){
            PatientEntity entity = patient.get();
            entity.updateData(p);
        }
    }

    @DeleteMapping("/id")
    @Transactional
    public void deletePatient(Long id){
        Optional<PatientEntity> patient = repository.findById(id);
        if(patient.isPresent()){
            PatientEntity finalPatient = patient.get();
            finalPatient.deactivate();
        } else {
            throw new IllegalArgumentException("Erro ao desativar paciente");
        }
    }
}
