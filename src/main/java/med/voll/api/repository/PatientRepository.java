package med.voll.api.repository;

import med.voll.api.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity,Long> {
    Optional<PatientEntity> findOneByIdAndStatusTrue(Long id);
}
