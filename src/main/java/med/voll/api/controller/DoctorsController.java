package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorsDataList;
import med.voll.api.dto.DoctorDto;
import med.voll.api.entity.DoctorEntity;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DoctorsDataList> buscaMedicos(@PageableDefault(size = 10, sort = {"especialidade","nome","crm"}) Pageable pagination){
      return repository.findAll(pagination).map(DoctorsDataList::new);
    }
}
