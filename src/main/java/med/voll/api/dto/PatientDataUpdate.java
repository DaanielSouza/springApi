package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record PatientDataUpdate(@NotNull Long id, String nome, String telefone, Address endereco) {

}
