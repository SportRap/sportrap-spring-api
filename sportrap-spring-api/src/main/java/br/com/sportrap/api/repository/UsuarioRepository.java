package br.com.sportrap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sportrap.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT DISTINCT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario")
	public Usuario buscarUsuarioComNomeExistente(@Param("nomeUsuario") String nomeUsuario);

	@Query("SELECT DISTINCT u FROM Usuario u WHERE u.email = :emailUsuario")
	public Usuario buscarUsuarioComEmailExistente(@Param("emailUsuario") String emailUsuario);

	@Query("SELECT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario AND u.senha = :senhaUsuario")
	public Usuario verificarCredenciaisLogin(@Param("nomeUsuario") String nomeUsuario,
			@Param("senhaUsuario") String senhaUsuario);

}
