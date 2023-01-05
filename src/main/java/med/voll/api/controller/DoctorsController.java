package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorsDataList;
import med.voll.api.dto.DoctorDto;
import med.voll.api.entity.DoctorEntity;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class DoctorsController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedicos(@RequestBody @Valid DoctorDto medico){
        repository.save(new DoctorEntity(medico));
    }

    @GetMapping
    public List<DoctorsDataList> buscaMedicos(){
      return repository.findAll().stream().map(DoctorsDataList::new).toList();
    }
}
