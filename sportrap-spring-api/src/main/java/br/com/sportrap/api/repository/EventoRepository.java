package br.com.sportrap.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sportrap.api.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

	@Query("SELECT e FROM Evento e WHERE e.nomeEvento = ':nomeEvento'")
	public Evento buscarEventoComNomeExistente(String nomeEvento);

	@Query("SELECT e FROM Evento e WHERE e.nomeEvento = '%:palavra%' ORDER BY e.nomeEvento")
	public List<Evento> filtrarEventos(String palavra);

}
