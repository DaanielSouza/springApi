package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDataDto(@NotBlank String user,@NotBlank String password) {
}
