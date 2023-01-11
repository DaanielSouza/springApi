package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorDataUpdate;
import med.voll.api.dto.DoctorsDataList;
import med.voll.api.dto.DoctorDto;
import med.voll.api.dto.PatientDto;
import med.voll.api.entity.DoctorEntity;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class DoctorsController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDto> addDoctor(@RequestBody @Valid DoctorDto doc, UriComponentsBuilder uriBuilder) {
        DoctorEntity doctor = new DoctorEntity(doc);
        repository.save(doctor);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDto(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorsDataList>> findDoctors(@PageableDefault(size = 10, sort = {"especialidade", "nome", "crm"}) Pageable pagination) {
        var page = repository.findAllByStatusTrue(pagination).map(DoctorsDataList::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> findDoctor(@PathVariable Long id) {
        Optional<DoctorEntity> doctorOpt = repository.findOneByIdAndStatusTrue(id);
        return ResponseEntity.ok(new DoctorDto(doctorOpt.get()));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody @Valid DoctorDataUpdate medico) {
        Optional<DoctorEntity> doc = repository.findById(medico.id());
        DoctorEntity doctor = doc.get();
        doctor.updateData(medico);
        return ResponseEntity.ok(new DoctorDto(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        Optional<DoctorEntity> doc = repository.findById(id);
        DoctorEntity doctor = doc.get();
        doctor.deactivateDoctor();
        return ResponseEntity.noContent().build();
    }
}
