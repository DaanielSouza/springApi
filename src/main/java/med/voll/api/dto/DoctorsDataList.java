package med.voll.api.dto;

import med.voll.api.entity.DoctorEntity;
import med.voll.api.entity.PatientEntity;

public record DoctorsDataList(Long id, String nome, String email, String crm, Speciality speciality) {

    public DoctorsDataList(DoctorEntity m){
        this(m.getId(),m.getNome(), m.getEmail(),m.getCrm(),m.getEspecialidade());
    }
}
