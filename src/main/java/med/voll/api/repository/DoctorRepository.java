package med.voll.api.repository;

import med.voll.api.entity.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Page<DoctorEntity> findAllByStatusTrue(Pageable pagination);

    Optional<DoctorEntity> findOneByIdAndStatusTrue(Long id);
}
