package br.com.sportrap.api.model.enums;

public enum SituacaoEventoEnum {

	AGU("Aguardando participantes"),
	AGE("Evento fechado e agendado"),
	FIN("Evento finalizado");

	private String descricao;

	private SituacaoEventoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
