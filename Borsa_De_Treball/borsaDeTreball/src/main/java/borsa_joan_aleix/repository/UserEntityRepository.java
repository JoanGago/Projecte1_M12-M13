package borsa_joan_aleix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import borsa_joan_aleix.models.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByUsername(String username);
}
