package br.com.sportrap.api.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

	@GetMapping("/novo")
	public boolean verificacaoUsuarioEEmailExistente(@Validated @RequestBody Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.buscarUsuarioComNomeExistente(usuario.getNomeUsuario());

		if (usuarioExistente != null) {
			// Já existe um usuário com esse nome ou email
			return true;
		} else {
			// Nome de usuário disponível para ser utilizado, verificar pelo
			// email
			usuarioExistente = usuarioRepository.buscarUsuarioComEmailExistente(usuario.getEmail());

			if (usuarioExistente != null) {
				// Nome e email já cadastrados
				return true;
			} else {
				return false;
			}
		}
	}

	@PostMapping("/novo")
	public boolean cadastrarNovoUsuario(@Validated @RequestBody Usuario usuarioNovo) {
		return usuarioRepository.save(usuarioNovo) != null;
	}

	@PostMapping("/senhaEsquecida")
	public boolean enviarEmailSenhaEsquecida(@Validated @RequestBody String emailUsuario) {
		Usuario usuarioCadastrado = usuarioRepository.buscarUsuarioComEmailExistente(emailUsuario);

		if (usuarioCadastrado != null) {
			StringBuilder mensagem = new StringBuilder();

			mensagem.append("<h3><b> Caro usuário, </b></h3> \n");
			mensagem.append("	esta é uma resposta automática ao seu pedido de senha esquecida. \n\n");
			mensagem.append(" 	Seus dados:");
			mensagem.append("		Nome de usário: <b>" + usuarioCadastrado.getNomeUsuario() + "</b> \n");
			mensagem.append("		Senha: <b>" + usuarioCadastrado.getSenha() + "</b> \n");
			mensagem.append(
					"<h3> Esta é uma mensagem automática. Não responda esse email. Caso haja dúvidas, envie um email para <i> suportesportrap@gmail.com  </i>. </h3> \n");

			return enviarLoginParaUsuario(emailUsuario, mensagem, "SportRap - Senha esquecida");
		} else {
			return false;
		}

	}

	private boolean enviarLoginParaUsuario(String emailUsuario, StringBuilder mensagem, String assunto) {
		String de = emailUsuario;
		String para = "suportesportrap@gmail.com";
		String host = "localhost";
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		
		Authenticator authenticator = new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("suportesportrap@gmail.com", "sportrap123");
		    }
		};

		Session session = Session.getDefaultInstance(properties, authenticator);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(para));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(de));

			// Set Subject: header field
			message.setSubject(assunto);

			// Now set the actual message
			message.setContent(mensagem, "text/html");

			Transport.send(message);
			System.out.println("Mensagem enviada com sucesso.");
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}
}
