package br.com.sportrap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sportrap.api.model.Usuario;
import br.com.sportrap.api.repository.UsuarioRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/listar")
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@PostMapping("/login")
	public boolean login(@Validated @RequestBody Usuario usuarioLogin) {
		if (usuarioRepository.verificarCredenciaisLogin(usuarioLogin.getNomeUsuario(),
				usuarioLogin.getSenha()) != null) {
			// Login realizado com sucesso
			return true;
		} else {
			// Credenciais inválidas
			return false;
		}
	}

	@GetMapping("/novo")
	public boolean verificacaoUsuarioExistente(@Validated @RequestBody String nomeUsuario) {
		Usuario usuarioExistente = usuarioRepository.buscarComNomeExistente(nomeUsuario);

		if (usuarioExistente != null) {
			// Já existe um usuário com esse nome
			return true;
		} else {
			// Nome de usuário disponível para ser utilizado
			return false;
		}
	}

	@PostMapping("/novo")
	public boolean cadastrarNovoUsuario(@Validated @RequestBody Usuario usuarioNovo) {
		return usuarioRepository.save(usuarioNovo) != null;
	}
}
