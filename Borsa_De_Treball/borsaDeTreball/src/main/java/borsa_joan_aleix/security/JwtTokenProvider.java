package borsa_joan_aleix.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import borsa_joan_aleix.models.UserEntity;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.util.Date;

@Component
public class JwtTokenProvider {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${app.security.jwt.secret}")
	private String jwtSecret;
	
	@Value("${app.security.jwt.expiration}")
	private Long jwtDurationSeconds;
	
	public String generateToken(Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		
		return Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
				.setHeaderParam("typ", "JWT")
				.setSubject(Long.toString(user.getId()))
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + (jwtDurationSeconds * 1000)))
				.claim("username", user.getUsername())
				.claim("email", user.getEmail())
				.compact();
		
	}
	
	public boolean isValidToken(String token) {
		if (!StringUtils.hasLength(token))
			return false;
		
		try {
			JwtParser validator = Jwts.parserBuilder()
					.setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
					.build();
			
			validator.parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.info("Error en la firma del token", e);
		} catch (MalformedJwtException | UnsupportedJwtException e) {
			log.info("Token Incorrecto", e);
		} catch (ExpiredJwtException e) {
			log.info("Token expirado", e);
		}
		return false;
	}

	public String getUsernameFromToken(String token) {
		return ((JwtParser) Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))).parseClaimsJws(token).getBody().getSubject();
	}
	
}
