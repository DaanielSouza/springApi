package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.PacienteDto;
import med.voll.api.entity.PacienteEntity;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid PacienteDto paciente){
        repository.save(new PacienteEntity(paciente));
    }
}
