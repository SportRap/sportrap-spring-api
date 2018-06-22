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
import br.com.sportrap.api.model.enums.EsporteEnum;
import br.com.sportrap.api.repository.EventoRepository;
import br.com.sportrap.api.repository.UsuarioRepository;
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
	
	@PostMapping("/salvar")
	public boolean salvarEvento(@Validated @RequestBody Evento evento) {
		return eventoRepository.save(evento) != null;
	}

	@PostMapping("/novo")
	public boolean criarNovoEvento(@Validated @RequestBody Evento novoEvento) {
		if (eventoRepository.buscarEventoComNomeExistente(novoEvento.getNomeEvento()) != null) {
			// Já possui um evento criado com esse nome
			return false;
		} else {
			// Evento com nome disponível para criação.

//			novoEvento.setCriadorEvento(criadorEvento);
			novoEvento.setMembrosTime1(new ArrayList<>());
			novoEvento.setMembrosTime2(new ArrayList<>());

			if (eventoRepository.save(novoEvento) != null) {
				return true;
			}
		}

		return false;
	}

	@PostMapping("/entrar/{idEvento}/{idUsuario}/{time}")
	public boolean participarDeEvento(@Validated @RequestHeader(value = "usuario") long idUsuario,
			@Validated @RequestHeader(value = "idEvento") long idEvento,
			@Validated @RequestHeader(value = "time") int time) {

		Usuario usuarioParticipante = usuarioRepository.findById(idUsuario).get();
		Evento eventoEscolhido = eventoRepository.findById(idEvento).get();

		if (Funcoes.timeAceitaParticipantes(eventoEscolhido, time)) {
			switch (time) {
			case 1:
				eventoEscolhido.getMembrosTime1().add(usuarioParticipante);
				break;
			case 2:
				eventoEscolhido.getMembrosTime2().add(usuarioParticipante);
				break;
			}
			return eventoRepository.save(eventoEscolhido) != null; // Se o
																	// retorno
																	// do save
																	// não é
																	// nulo o
																	// usuario
																	// está
																	// participando
		}
		// Erro ao participar do evento. Os erros de evento privado ou
		// evento já cheio devem ser tratados pelo front.
		return false;

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
		mensagem.append(" Você foi convidado por <i>" + eventoConvite.getCriadorEvento().getNomeCompleto() + "</i>");
		mensagem.append(" para participar do evento: " + eventoConvite.getNomeEvento() + " que será realizado em "
				+ eventoConvite.getLocalEvento());
		mensagem.append(" para aceitar o convite e acompanhar as notificações do mesmo, acesse o link :" + link);

		if (usuarioConvidado == null) {
			// Usuário não existente
			link = pagina + "//novo//" + idEvento + "//";
			pagina += time + "//" + email;
			mensagem.append(" e faça o seu cadastro no SportRap.");
		} else {
			// Usuario existe
			link = pagina + "//entrar//" + idEvento + "//";
			pagina += time + "//" + usuarioConvidado.getId();
		}

		return Funcoes.enviarEmail(email, mensagem, "Você foi convidado para um evento em SportRap");
	}

	@PostMapping("/novo/{idEvento}/{time}/{emailParticipante}")
	public boolean cadastrarNovoUsuarioConvidado(@Validated @RequestHeader(value = "idEvento") long idEvento,
			@Validated @RequestHeader(value = "time") int time,
			@Validated @RequestHeader(value = "emailParticipante") String emailParticipante) {
		// Buscar se já existe o usuário pois ele pode ter recebido mais de um
		// convite antes de estar cadastrado

		Usuario usuarioNovo = usuarioRepository.buscarUsuarioComEmailExistente(emailParticipante);

		if (usuarioNovo == null) {
			usuarioNovo = new Usuario();
			usuarioNovo.setEmail(emailParticipante);
		}

		if (usuarioRepository.save(usuarioNovo) != null && participarDeEvento(usuarioNovo.getId(), idEvento, time)) {
			// Vínculo ao evento salvo com sucesso
			return true;
		} else {
			// Erro ao salvar evento
			return false;
		}

	}

	@PostMapping("/solicitar/{idEvento}/{time}/{idUsuario}")
	public boolean solicitarParticipacaoEvento(@Validated @RequestHeader(value = "idEvento") long idEvento,
			@Validated @RequestHeader(value = "time") String time,
			@Validated @RequestHeader(value = "idUsuario") long idUsuario) {
		Usuario usuarioParticipante = usuarioRepository.findById(idUsuario).get();
		Evento evento = eventoRepository.findById(idEvento).get();

		StringBuilder mensagem = new StringBuilder();

		String pagina = "localhost/8080";

		mensagem.append("<h2><b> Caro usuário, </b></h2><br/>");
		mensagem.append(" O usuário de nome <i>" + usuarioParticipante.getNomeCompleto() + "</i>");
		mensagem.append(" solicitou a partipação para seu evento: " + evento.getNomeEvento() + " que será realizado em "
				+ evento.getLocalEvento() + ".");
		mensagem.append(" Para confirmar a participação desse usuário, acesse o link :");
		mensagem.append(pagina + "//entrar//" + idUsuario + "//" + time + "//" + idEvento);

		return Funcoes.enviarEmail(evento.getCriadorEvento().getEmail(), mensagem, "SportRap - Confirmação de convite");
	}

	@GetMapping("/descricaoEsportes")
	public EsporteEnum[] descricaoEsportes() {
		return EsporteEnum.values();
	}

}
