package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.Endereco;
import med.voll.api.dto.PacienteDto;

@Entity(name = "pacientes")
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    private String telefone;

    private String cpf;

    private Endereco endereco;

    public PacienteEntity(PacienteDto p){
        this.email = p.email();
        this.telefone = p.telefone();
        this.nome = p.nome();
        this.endereco = new Endereco(p.endereco());
        this.cpf = p.cpf();
    }
}