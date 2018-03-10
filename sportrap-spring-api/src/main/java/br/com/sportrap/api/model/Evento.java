package br.com.sportrap.api.model;

import java.util.Date;
import java.util.List;

import br.com.sportrap.api.model.enums.EsporteEnum;

public class Evento {

	private int id;
	private String descricao;
	private Date dataEvento;
	private String localEvento;
	private EsporteEnum esporteEscolhido;
	private int maxJogadoresPorTime;
	private List<Usuario> time1;
	private List<Usuario> time2;
	private Usuario criadorEvento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getLocalEvento() {
		return localEvento;
	}

	public void setLocalEvento(String localEvento) {
		this.localEvento = localEvento;
	}

	public EsporteEnum getEsporteEscolhido() {
		return esporteEscolhido;
	}

	public void setEsporteEscolhido(EsporteEnum esporteEscolhido) {
		this.esporteEscolhido = esporteEscolhido;
	}

	public int getMaxJogadoresPorTime() {
		return maxJogadoresPorTime;
	}

	public void setMaxJogadoresPorTime(int maxJogadoresPorTime) {
		this.maxJogadoresPorTime = maxJogadoresPorTime;
	}

	public List<Usuario> getTime1() {
		return time1;
	}

	public void setTime1(List<Usuario> time1) {
		this.time1 = time1;
	}

	public List<Usuario> getTime2() {
		return time2;
	}

	public void setTime2(List<Usuario> time2) {
		this.time2 = time2;
	}

	public Usuario getCriadorEvento() {
		return criadorEvento;
	}

	public void setCriadorEvento(Usuario criadorEvento) {
		this.criadorEvento = criadorEvento;
	}

}
