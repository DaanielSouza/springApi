package med.voll.api.repository;

import med.voll.api.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    UserDetails findByLogin(String username);
}