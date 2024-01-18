package borsa_joan_aleix.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import borsa_joan_aleix.Service.UserEntityService;
import borsa_joan_aleix.dto.LoginRequest;
import borsa_joan_aleix.dto.LoginResponse;
import borsa_joan_aleix.dto.UserRegisterDTO;
import borsa_joan_aleix.models.UserEntity;
import borsa_joan_aleix.security.JwtTokenProvider;

@RestController
public class AuthController {
	
	@Autowired
	private UserEntityService userService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	
	@PostMapping("/auth/register")
	public UserEntity save(@RequestBody UserRegisterDTO userDTO) {
		return this.userService.save(userDTO);
	}
	
	@PostMapping("/auth/login")
	public LoginResponse login(@RequestBody LoginRequest loginDTO) {
		Authentication authDTO = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
		
		Authentication authentication = this.authManager.authenticate(authDTO);
		UserEntity user = (UserEntity) authentication.getPrincipal();
		
		String token = this.jwtTokenProvider.generateToken(authentication);
		
		return new LoginResponse(user.getUsername(),
				user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
				token);
		
	}

}
