package med.voll.api.dto;

import med.voll.api.entity.PatientEntity;

public record PatientsDataList(String nome, String email, String cpf) {

    public PatientsDataList(PatientEntity p){
        this(p.getNome(),p.getEmail(),p.getCpf());
    }
}
