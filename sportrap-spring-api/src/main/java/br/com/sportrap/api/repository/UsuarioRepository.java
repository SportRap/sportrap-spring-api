package br.com.sportrap.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sportrap.api.model.Usuario;
import br.com.sportrap.api.repository.interfaces.BaseRepository;

public interface UsuarioRepository extends BaseRepository<Usuario> {

	@Query("SELECT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario")
	public Usuario buscarComNomeExistente(@Param("nomeUsuario") String nomeUsuario);

	@Query("SELECT u FROM Usuario u WHERE u.nomeUsuario = :usuarioLogin.nomeUsuario AND u.senha = :usuario.senha")
	public Usuario verificarCredenciaisLogin(@Param("usuario") Usuario usuarioLogin);

}
