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
	public List<Evento> filtrarEventos(@Validated @RequestBody String palavra){
		return eventoRepository.filtrarEventos(palavra);
	}

	@PostMapping("/novo")
	public boolean criarNovoEvento(@Validated @RequestBody Evento novoEvento, @Validated @RequestBody Usuario criadorEvento) {
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

}
