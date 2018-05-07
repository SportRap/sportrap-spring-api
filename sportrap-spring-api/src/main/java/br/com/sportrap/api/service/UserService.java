package br.com.sportrap.api.service;

import org.springframework.stereotype.Component;


import br.com.sportrap.api.model.Usuario;

@Component
public interface UserService {

	Usuario findByEmail(String email);

}
