package br.com.sportrap.api.model;

import java.util.Date;

import br.com.sportrap.api.model.enums.EsporteEnum;

public class Usuario {
	
	private int id;
	private String nomeCompleto;
	private Date dataNascimento;
	private String nomeUsuario;
	private String email;
	private String senha;
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
