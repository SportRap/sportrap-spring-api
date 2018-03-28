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

			mensagem.append("<h2><b> Caro usuário, </b></h2> <br/>");
			mensagem.append("Esta é uma resposta automática ao seu pedido de senha esquecida. <br/><br/>");
			mensagem.append("Seus dados:<br/>");
			mensagem.append("		Nome de usário: <b>" + usuarioCadastrado.getNomeUsuario() + "</b> <br/>");
			mensagem.append("		Senha: <b>" + usuarioCadastrado.getSenha() + " </b> \b");
			mensagem.append(
					"<h5> Esta é uma mensagem automática. Não responda esse email. Caso haja dúvidas, envie um email para <i> suportesportrap@gmail.com  </i>. </h5> <br/><br/>");

			return enviarLoginParaUsuario(emailUsuario, mensagem, "SportRap - Senha esquecida");
		} else {
			return false;
		}

	}

	private boolean enviarLoginParaUsuario(String emailUsuario, StringBuilder mensagem, String assunto) {
		String de = emailUsuario;
		String para = "suportesportrap@gmail.com";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.user", "SportRap");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.EnableSSL.enable", "true");

		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallbac k", "false");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.port", "465");

		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("suportesportrap@gmail.com", "sportrap123");
			}
		};

		Session session = Session.getDefaultInstance(properties, authenticator);

		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(para));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(de));
			message.setSubject(assunto);
			message.setContent(mensagem.toString(), "text/html");

			Transport.send(message);

			System.out.println("Mensagem enviada com sucesso.");
			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}
}
