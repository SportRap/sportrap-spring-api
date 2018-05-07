package br.com.sportrap.api.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import static br.com.sportrap.api.security.config.SecurityConstants.EXPIRATION_TIME;
import static br.com.sportrap.api.security.config.SecurityConstants.SECRET;
import java.io.IOException;
import java.util.Date;

@Component
public class JWTAuthenticationFilter {

	public String generateToken(Authentication auth) throws IOException, ServletException {

		return Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();

		// res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}