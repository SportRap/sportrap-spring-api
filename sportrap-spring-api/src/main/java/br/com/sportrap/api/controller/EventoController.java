package br.com.sportrap.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sportrap.api.model.Evento;
import br.com.sportrap.api.model.Usuario;
import br.com.sportrap.api.repository.EventoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/evento")
public class EventoController {

	@Autowired
	EventoRepository eventoRepository;

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
		
		if(eventoRepository.save(eventoEscolhido) != null){
			// Usuário participando do evento
			return true;
		} else {
			// Erro ao participar do evento. Os erros de evento privado ou evento já cheio devem ser tratados pelo front.
			return false;
		}

	}

}
