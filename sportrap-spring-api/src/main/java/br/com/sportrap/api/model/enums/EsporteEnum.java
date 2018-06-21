package br.com.sportrap.api.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EsporteEnum {
	
	BASQUETE("BASQUETE" , "Basquete de quadra", 10),
	FUTEBOL("FUTEBOL", "Futebol", 11),
	VOLEI_QUADRA("VOLEI_QUADRA", "Vôlei de quadra", 6),
	FUTSAL("FUTSAL", "Futsal", 5),
	TENIS_QUADRA("TENIS_QUADRA", "Tênis de quadra", 2),
	TENIS_MESA("TENIS_MESA", "Tênis de mesa", 2);
	
	private String esporteBanco;
	private String descricao;
	private int jogadoresPorTime;
	
	private EsporteEnum(String esporteBanco,String descricao, int jogadoresPorTime) {
		this.esporteBanco = esporteBanco;
		this.descricao = descricao;
		this.jogadoresPorTime = jogadoresPorTime;
	}
	
	public String getEsporteBanco() {
		return esporteBanco;
	}

	public void setEsporteBanco(String esporteBanco) {
		this.esporteBanco = esporteBanco;
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
