package br.com.sportrap.api.model.enums;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum SituacaoEventoEnum {

	AGU("Aguardando participantes"),
	AGE("Evento fechado e agendado"),
	FIN("Evento finalizado");
	
	
	private SituacaoEventoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name="ENUM_JOGADORES_TIME") 
	@Enumerated(EnumType.STRING) 
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
