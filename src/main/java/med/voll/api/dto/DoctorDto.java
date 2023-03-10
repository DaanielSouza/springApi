package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.entity.DoctorEntity;

public record DoctorDto(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Speciality especialidade,
        @NotBlank
        String telefone,
        @NotNull
        @Valid
        Address endereco) {
    public DoctorDto(DoctorEntity doctor) {
            this(doctor.getNome(),doctor.getEmail(),doctor.getCrm(),doctor.getEspecialidade(),doctor.getTelefone(),doctor.getEndereco());
    }
}
