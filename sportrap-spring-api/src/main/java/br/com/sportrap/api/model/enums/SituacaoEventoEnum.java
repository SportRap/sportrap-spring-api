package br.com.sportrap.api.model.enums;

public enum SituacaoEventoEnum {

	AGU("Aguardando participantes"),
	AGE("Evento fechado e agendado"),
	FIN("Evento finalizado");
	
	private SituacaoEventoEnum(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
