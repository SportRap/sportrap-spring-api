package br.com.sportrap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sportrap.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario")
	public Usuario buscarComNomeExistente(@Param("nomeUsuario") String nomeUsuario);

	@Query("SELECT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario AND u.senha = :senhaUsuario")
	public Usuario verificarCredenciaisLogin(@Param("nomeUsuario") String nomeUsuario,
			@Param("senhaUsuario") String senhaUsuario);

}
