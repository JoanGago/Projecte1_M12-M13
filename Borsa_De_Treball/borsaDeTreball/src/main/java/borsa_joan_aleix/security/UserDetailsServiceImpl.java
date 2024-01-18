package borsa_joan_aleix.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import borsa_joan_aleix.Service.UserEntityService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	private final UserEntityService userService;
	
	public UserDetailsServiceImpl(UserEntityService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("loadUserByUsername {}", username);
		return this.userService.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(username + " no encontrado")
		);
	}

}
