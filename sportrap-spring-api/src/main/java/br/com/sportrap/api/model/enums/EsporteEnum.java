package br.com.sportrap.api.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EsporteEnum {
	
	BASQUETE("Basquete de quadra", 10),
	FUTEBOL("Futebol", 11),
	VOLEI_QUADRA("Vôlei de quadra",6),
	FUTSAL("Futsal", 5),
	TENIS_QUADRA("Tênis de quadra", 2),
	TENIS_MESA("Tênis de mesa", 2);
	
	private int jogadoresPorTime;
	private String descricao;

	private EsporteEnum(String descricao, int jogadoresPorTime) {
		this.descricao = descricao;
		this.jogadoresPorTime = jogadoresPorTime;
	}

	@JsonValue
	public int getJogadoresPorTime() {
		return jogadoresPorTime;
	}

	public void setJogadoresPorTime(int jogadoresPorTime) {
		this.jogadoresPorTime = jogadoresPorTime;
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
