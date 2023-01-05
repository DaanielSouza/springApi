package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorDataUpdate;
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
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class DoctorsController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void addDoctor(@RequestBody @Valid DoctorDto medico){
        repository.save(new DoctorEntity(medico));
    }

    @GetMapping
    public Page<DoctorsDataList> findDoctors(@PageableDefault(size = 10, sort = {"especialidade","nome","crm"}) Pageable pagination){
      return repository.findAll(pagination).map(DoctorsDataList::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DoctorDataUpdate medico){
        Optional<DoctorEntity> doc = repository.findById(medico.id());
        if(doc.isPresent()){
            DoctorEntity doctor = doc.get();
            doctor.updateData(medico);
        } else {
            throw new IllegalArgumentException("Médico não encontrado em nosso banco de dados");
        }
    }
}
