package br.com.sportrap.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import br.com.sportrap.api.model.enums.EsporteEnum;

@Entity(name = "EVENTO")
public class Evento {
	@Id
	private long id;
	
	@Column (name = "DESCRICAO_EVENTO")
	private String descricao;
	
	@Column (name = "DATA_EVENTO")
	private Date dataEvento;
	
	@Column (name = "LOCAL_EVENTO")
	private String localEvento;
	
	@Column
	private EsporteEnum esporteEscolhido;
	
	@Column
	private int maxJogadoresPorTime;
	
	@ElementCollection
	private List<Usuario> time1;
	@ElementCollection
	private List<Usuario> time2;
	
	@Column (name = "CRIADOR_EVENTO")
	private Usuario criadorEvento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
