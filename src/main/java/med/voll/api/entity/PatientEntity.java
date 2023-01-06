package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.Address;
import med.voll.api.dto.PatientDataUpdate;
import med.voll.api.dto.PatientDto;

@Entity(name = "pacientes")
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    private String telefone;

    private String cpf;

    private Address endereco;

    private Boolean status;

    public PatientEntity(PatientDto p){
        this.email = p.email();
        this.telefone = p.telefone();
        this.nome = p.nome();
        this.endereco = new Address(p.endereco());
        this.cpf = p.cpf();
        this.status = true;
    }

    public void updateData(PatientDataUpdate p) {
        if(p.nome() != null){
            this.nome = p.nome();
        }
        if(p.telefone() != null){
            this.telefone = p.telefone();
        }
        if(p.endereco() != null){
            this.endereco = new Address(p.endereco());
        }
    }

    public void deactivate() {
        this.status = false;
    }
}