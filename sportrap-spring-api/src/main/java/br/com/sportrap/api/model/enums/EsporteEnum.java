package br.com.sportrap.api.model.enums;

public enum EsporteEnum {
	BASQUETE(10),
	FUTEBOL(11),
	VOLEIBOL_QUADRA(6),
	FUTSAL(5);

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
