package med.voll.api.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
    }
}
