package borsa_joan_aleix.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import borsa_joan_aleix.dto.UserRegisterDTO;
import borsa_joan_aleix.models.UserAuthority;
import borsa_joan_aleix.models.UserEntity;
import borsa_joan_aleix.repository.UserEntityRepository;

@Service
public class UserEntityService {
	
	private final UserEntityRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	public UserEntityService(UserEntityRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Optional<UserEntity> findByUsername(String username){
		return this.repository.findByUsername(username);
	}
	
	public UserEntity save(UserRegisterDTO userDTO) {
		UserEntity user = new UserEntity(
				null,
				userDTO.username(),
				passwordEncoder.encode(userDTO.password()),
				userDTO.email(),
				List.of(UserAuthority.READ)
		);
		return this.repository.save(user);
	}

}
