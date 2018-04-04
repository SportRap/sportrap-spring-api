package br.com.sportrap.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sportrap.api.model.Usuario;
import br.com.sportrap.api.repository.UsuarioRepository;
import br.com.sportrap.api.utils.Funcoes;

@RestController
@CrossOrigin("*")
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

	@PostMapping("/novo")
	public boolean cadastrarNovoUsuario(@Validated @RequestBody Usuario usuarioNovo) {
		return usuarioRepository.save(usuarioNovo) != null;
	}

	@GetMapping("/novo/nome/{nomeUsuario}")
	public boolean verificarNomeUsuarioLivreParaUso(
			@Validated @RequestHeader(value = "nomeUsuario") String nomeUsuario) {
		Usuario usuarioExistente = usuarioRepository.buscarUsuarioComNomeExistente(nomeUsuario);

		if (usuarioExistente != null) {
			// Já existe um usuário com esse nome
			return false;
		} else {
			// Nome livre para ser utilizado
			return true;
		}
	}

	@GetMapping("/novo/email/{emailUsuario}")
	public boolean verificarEmailUsuarioLivreParaUso(
			@Validated @RequestHeader(value = "emailUsuario") String emailUsuario) {
		Usuario usuarioExistente = usuarioRepository.buscarUsuarioComEmailExistente(emailUsuario);

		if (usuarioExistente != null) {
			// Já existe um usuário com esse email
			return false;
		} else {
			// Email livre para ser utilizado
			return true;
		}
	}

	@PostMapping("/senhaEsquecida")
	public boolean enviarEmailSenhaEsquecida(@Validated @RequestBody String emailUsuario) {
		Usuario usuarioCadastrado = usuarioRepository.buscarUsuarioComEmailExistente(emailUsuario);

		if (usuarioCadastrado != null) {
			StringBuilder mensagem = new StringBuilder();

			mensagem.append("<h2><b> Caro usuário, </b></h2> <br/>");
			mensagem.append("Esta é uma resposta automática ao seu pedido de senha esquecida. <br/><br/>");
			mensagem.append("Seus dados:<br/>");
			mensagem.append("		Nome de usário: <b>" + usuarioCadastrado.getNomeUsuario() + "</b> <br/>");
			mensagem.append("		Senha: <b>" + usuarioCadastrado.getSenha() + " </b> \b");
			mensagem.append(
					"<h5> Esta é uma mensagem automática. Não responda esse email. Caso haja dúvidas, envie um email para <i> suportesportrap@gmail.com  </i>. </h5> <br/><br/>");

			return Funcoes.enviarEmail(emailUsuario, mensagem, "SportRap - Senha esquecida");
		} else {
			return false;
		}

	}
}
