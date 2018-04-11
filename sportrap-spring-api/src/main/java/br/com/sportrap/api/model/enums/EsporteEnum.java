package br.com.sportrap.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EsporteEnum {
	
	BASQUETE("Basquete de quadra", 10),
	FUTEBOL("Futebol", 11),
	VOLEI_QUADRA("Vôlei de quadra",6),
	FUTSAL("Futsal", 5),
	TENIS_QUADRA("Tênis de quadra", 2),
	TENIS_MESA("Tênis de mesa", 2);
	
	@JsonValue
	private int jogadoresPorTime;
	@JsonValue
	private String descricao;

	private EsporteEnum(String descricao, int jogadoresPorTime) {
		this.descricao = descricao;
		this.jogadoresPorTime = jogadoresPorTime;
	}

	public int getJogadoresPorTime() {
		return jogadoresPorTime;
	}

	public void setJogadoresPorTime(int jogadoresPorTime) {
		this.jogadoresPorTime = jogadoresPorTime;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
