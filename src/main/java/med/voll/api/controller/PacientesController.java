package med.voll.api.controller;

import med.voll.api.dto.PacienteDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PacientesController {

    public PacienteDto cadastrarPaciente(@RequestBody PacienteDto json){
        return json;
    }
}
