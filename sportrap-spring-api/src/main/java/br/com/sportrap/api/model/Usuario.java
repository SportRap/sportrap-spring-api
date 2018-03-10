package br.com.sportrap.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.annotation.Id;

import br.com.sportrap.api.model.enums.EsporteEnum;
@Entity
public class Usuario {
	
	@Id
	private int id;
	@Column (name = "NOME_COMPLETO")	
	private String nomeCompleto;
	@Column (name = "DATA_NASC")
	private Date dataNascimento;
	@Column (name = "NOME_USUARIO")
	private String nomeUsuario;
	@Column (name = "EMAIL")
	private String email;
	@Column (name = "SENHA")
	private String senha;
	
	@Enumerated(EnumType.ORDINAL)
	@Column (name = "ESPORTE_FAVORITO")
	private EsporteEnum esporteFavorito;
	
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
	public EsporteEnum getEsporteFavorito() {
		return esporteFavorito;
	}
	public void setEsporteFavorito(EsporteEnum esporteFavorito) {
		this.esporteFavorito = esporteFavorito;
	}

}
