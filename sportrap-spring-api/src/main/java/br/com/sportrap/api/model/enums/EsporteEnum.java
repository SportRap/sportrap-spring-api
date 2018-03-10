package br.com.sportrap.api.model.enums;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum EsporteEnum {
	BASQUETE(10),
	FUTEBOL(11),
	VOLEIBOL_QUADRA(6),
	FUTSAL(5);
	
	@Column(name="ENUM_JOGADORES_TIME") 
	@Enumerated(EnumType.STRING) 
	private int jogadoresPorTime;

	private EsporteEnum(int jogadoresPorTime) {
		this.jogadoresPorTime = jogadoresPorTime;
	}

	public int getJogadoresPorTime() {
		return jogadoresPorTime;
	}

	public void setJogadoresPorTime(int jogadoresPorTime) {
		this.jogadoresPorTime = jogadoresPorTime;
	}
}
