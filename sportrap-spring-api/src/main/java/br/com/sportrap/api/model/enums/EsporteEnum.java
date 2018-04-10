package br.com.sportrap.api.model.enums;

public enum EsporteEnum {
	
	BSQT("Basquete de quadra", 10),
	FUTE("Futebol", 11),
	VOLQ("Vôlei de quadra",6),
	FUTS("Futsal", 5);
	
	private int jogadoresPorTime;
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
