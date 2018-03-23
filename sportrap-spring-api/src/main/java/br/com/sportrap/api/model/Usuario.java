package br.com.sportrap.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sportrap.api.model.enums.EsporteEnum;

@Entity
@Table(name = "usuario")
@RequestMapping("/usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 343165237932713051L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "nome_completo")
	private String nomeCompleto;

	@Column(name = "nome_usuario")
	private String nomeUsuario;

	@Column(name = "senha")
	private String senha;

	@Column(name = "email")
	private String email;

	@Column(name = "data_nascimento")
	private Date dataNascimento;

	@Column(name = "esporte_favorito_enum")
	@Enumerated(EnumType.STRING)
	private EsporteEnum esporteFavoritoEnum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EsporteEnum getEsporteFavoritoEnum() {
		return esporteFavoritoEnum;
	}

	public void setEsporteFavoritoEnum(EsporteEnum esporteFavoritoEnum) {
		this.esporteFavoritoEnum = esporteFavoritoEnum;
	}

}
