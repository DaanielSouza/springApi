package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.PatientDto;
import med.voll.api.dto.PatientsDataList;
import med.voll.api.entity.PatientEntity;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<PatientsDataList> findPatients(){
        return repository.findAll().stream().map(PatientsDataList::new).toList();
    }
}
