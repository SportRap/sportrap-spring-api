package br.com.sportrap.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import br.com.sportrap.api.model.Usuario;
import br.com.sportrap.api.repository.UsuarioRepository;
import br.com.sportrap.api.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UsuarioRepository userRepository;

	public Usuario findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	
}
