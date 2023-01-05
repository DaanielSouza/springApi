package med.voll.api.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Address {
    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;
    private String numero;
    private String complemento;

    public Address(Address address) {
        this.logradouro = address.getLogradouro();
        this.cep = address.getCep();
        this.bairro = address.getBairro();
        this.cidade = address.getCidade();
        this.uf = address.getUf();
        this.numero = address.getNumero();
        this.complemento = address.getComplemento();
    }
}
