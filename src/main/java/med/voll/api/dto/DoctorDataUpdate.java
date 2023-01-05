package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record DoctorDataUpdate(
        @NotNull
        Long id,
        String telefone,
        String nome,
        Address endereco
    ) {
}
