package med.voll.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.Address;
import med.voll.api.dto.Speciality;
import med.voll.api.dto.DoctorDto;

@Table(name = "medicos")
@Entity(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;

    private String telefone;
    @Enumerated(EnumType.STRING)
    private Speciality especialidade;

    @Embedded
    private Address endereco;

    public DoctorEntity(DoctorDto medico) {
        this.nome = medico.nome();
        this.email = medico.email();
        this.crm = medico.crm();
        this.especialidade = medico.especialidade();
        this.endereco = new Address(medico.endereco());
        this.telefone = medico.telefone();
    }
}
