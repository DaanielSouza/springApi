package med.voll.api.controller;

import med.voll.api.dto.MedicoDto;
import med.voll.api.entity.MedicoEntity;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedicos(@RequestBody MedicoDto medico){
        repository.save(new MedicoEntity(medico));
    }
}
