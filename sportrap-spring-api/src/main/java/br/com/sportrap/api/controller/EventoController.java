package br.com.sportrap.api.controller;

import java.util.ArrayList;
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

import br.com.sportrap.api.model.Evento;
import br.com.sportrap.api.model.Usuario;
import br.com.sportrap.api.repository.EventoRepository;
import br.com.sportrap.api.repository.UsuarioRepository;
import br.com.sportrap.api.utils.Criptografia;
import br.com.sportrap.api.utils.Funcoes;

@RestController
@CrossOrigin("*")
@RequestMapping("/evento")
public class EventoController {

	@Autowired
	EventoRepository eventoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/listar")
	public List<Evento> listarEventos() {
		return eventoRepository.findAll();
	}

	@GetMapping("/filtrar/{id}")
	public List<Evento> filtrarEventos(@Validated @RequestBody String palavra) {
		return eventoRepository.filtrarEventos(palavra);
	}

	@PostMapping("/novo")
	public boolean criarNovoEvento(@Validated @RequestBody Evento novoEvento,
			@Validated @RequestBody Usuario criadorEvento) {
		if (eventoRepository.buscarEventoComNomeExistente(novoEvento.getNomeEvento()) != null) {
			// Já possui um evento criado com esse nome
			return false;
		} else {
			// Evento com nome disponível para criação.

			novoEvento.setCriadorEvento(criadorEvento);
			novoEvento.setMembrosTime1(new ArrayList<>());
			novoEvento.setMembrosTime2(new ArrayList<>());

			if (eventoRepository.save(novoEvento) != null) {
				return true;
			}
		}

		return false;
	}

	@PostMapping("/entrar")
	public boolean participarDeEvento(@Validated @RequestBody Usuario usuario, @Validated @RequestBody long idEvento,
			@Validated @RequestBody String time) {
		Evento eventoEscolhido = eventoRepository.findById(idEvento).get();

		switch (time) {
		case "1":
			eventoEscolhido.getMembrosTime1().add(usuario);
			break;
		case "2":
			eventoEscolhido.getMembrosTime2().add(usuario);
			break;
		}

		if (eventoRepository.save(eventoEscolhido) != null) {
			// Usuário participando do evento
			return true;
		} else {
			// Erro ao participar do evento. Os erros de evento privado ou
			// evento já cheio devem ser tratados pelo front.
			return false;
		}

	}

	@PostMapping("/convidar")
	public boolean convidarParaEvento(@Validated @RequestBody long idEvento, @Validated @RequestBody String time,
			@Validated @RequestBody String email) {
		Usuario usuarioConvidado = usuarioRepository.buscarUsuarioComEmailExistente(email);

		Evento eventoConvite = eventoRepository.findById(idEvento).get();
		
		String pagina = "localhost/8080";
		String link = "";

		
		StringBuilder mensagem = new StringBuilder();

		mensagem.append("<h2><b> Caro Usuario, </b></h2><br/>");
		mensagem.append(
				" Você foi convidado por <i>" + eventoConvite.getCriadorEvento().getNomeCompleto() + "</i>");
		mensagem.append(" para participar do evento: " + eventoConvite.getNomeEvento() + " que será realizado em "
				+ eventoConvite.getLocalEvento());
		mensagem.append(" para aceitar o convite e acompanhar as notificações do mesmo, acesse o link :" + link);
		
		if (usuarioConvidado == null) {
			// Usuário não existente
			link = pagina + "//novo//" + Criptografia.encriptar(String.valueOf(idEvento)) + "//";
			pagina += Criptografia.encriptar(time) + "//" + Criptografia.encriptar(email);

			mensagem.append(" e faça o seu cadastro no SportRap.");
		} else {
			link = pagina + "//Entrar//" + Criptografia.encriptar(String.valueOf(idEvento)) + "//";
			pagina += Criptografia.encriptar(time) + "//" + Criptografia.encriptar(Long.toString(usuarioConvidado.getId()));

		}
		

		Funcoes.enviarEmail(email, mensagem, "Você foi convidado para um evento em SportRap");

		return false;
	}

	@PostMapping("/novo/{idEvento}/{time}/{emailParticipante}")
	public boolean cadastrarNovoUsuarioConvidado(@Validated @RequestHeader(value = "idEvento") long idEvento,
			@Validated @RequestHeader(value = "time") String time,
			@Validated @RequestHeader(value = "emailParticipante") String emailParticipante) {
		// Buscar se já existe o usuário pois ele pode ter recebido mais de um
		// convite antes de estar cadastrado
		Usuario usuarioNovo = usuarioRepository.buscarUsuarioComEmailExistente(emailParticipante);

		if (usuarioNovo == null) {
			usuarioNovo = new Usuario();
			usuarioNovo.setEmail(emailParticipante);
		}

		if (usuarioRepository.save(usuarioNovo) != null && participarDeEvento(usuarioNovo, idEvento, time)) {
			// Vínculo ao evento salvo com sucesso
			return true;
		} else {
			// Erro ao salvar evento
			return false;
		}

	}

	public boolean solicitarParticipacaoEvento() {
		return false;
	}

}
