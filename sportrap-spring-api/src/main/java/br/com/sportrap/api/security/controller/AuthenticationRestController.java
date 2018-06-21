package br.com.sportrap.api.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sportrap.api.model.CurrentUser;
import br.com.sportrap.api.model.Usuario;
import br.com.sportrap.api.security.jwt.JWTAuthenticationFilter;
import br.com.sportrap.api.service.UserService;
import static br.com.sportrap.api.security.config.SecurityConstants.TOKEN_PREFIX;


@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTAuthenticationFilter jwtTokenUtil;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Usuario authenticationRequest, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
						authenticationRequest.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final String token = jwtTokenUtil.generateToken(authentication);
		
		response.setHeader("Authorization", TOKEN_PREFIX + token);
		
		final Usuario user = userService.findByEmail(authenticationRequest.getEmail());
		user.setSenha(null);
		return ResponseEntity.ok(new CurrentUser(token, user));
	}

}
