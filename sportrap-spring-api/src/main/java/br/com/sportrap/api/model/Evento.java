package br.com.sportrap.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import br.com.sportrap.api.model.enums.EsporteEnum;

@Entity
public class Evento implements Serializable {

	private static final long serialVersionUID = -3122717511654597318L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column (name = "descricao")
	private String descricao;
	
	@Column (name = "data_evento")
	private Date dataEvento;
	
	@Column (name = "local_evento")
	private String localEvento;
	
	@Column (name = "criador_evento")
	private Usuario criadorEvento;
	
	@Column(name="esporte_escolhido_enum") 
	@Enumerated(EnumType.STRING) 
	private EsporteEnum esporteEscolhidoEnum;
	
	@ElementCollection
	private List<Usuario> time1;
	
	@ElementCollection
	private List<Usuario> time2;
	

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

	public EsporteEnum getEsporteEscolhidoEnum() {
		return esporteEscolhidoEnum;
	}

	public void setEsporteEscolhidoEnum(EsporteEnum esporteEscolhidoEnum) {
		this.esporteEscolhidoEnum = esporteEscolhidoEnum;
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
