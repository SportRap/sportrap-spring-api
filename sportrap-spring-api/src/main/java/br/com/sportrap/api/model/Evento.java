package br.com.sportrap.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.sportrap.api.model.enums.EsporteEnum;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = -3122717511654597318L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "nome_evento")
	private String nomeEvento;

	@Column(name = "descricao_evento")
	private String descricaoEvento;

	@Column(name = "data_evento")
	private Date dataEvento;

	@Column(name = "local_evento")
	private String localEvento;
	
	@Column
	private boolean privado;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_criador_evento", nullable = false)
	private Usuario criadorEvento;

	@Column(name = "esporte_escolhido_enum")
	@Enumerated(EnumType.STRING)
	private EsporteEnum esporteEscolhidoEnum;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(joinColumns = { @JoinColumn(name = "id_evento") }, inverseJoinColumns = {
			@JoinColumn(name = "id_usuario") })
	private List<Usuario> membrosEvento;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getDescricaoEvento() {
		return descricaoEvento;
	}

	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
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
	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}

	public EsporteEnum getEsporteEscolhidoEnum() {
		return esporteEscolhidoEnum;
	}

	public void setEsporteEscolhidoEnum(EsporteEnum esporteEscolhidoEnum) {
		this.esporteEscolhidoEnum = esporteEscolhidoEnum;
	}

	public Usuario getCriadorEvento() {
		return criadorEvento;
	}

	public void setCriadorEvento(Usuario criadorEvento) {
		this.criadorEvento = criadorEvento;
	}

	public List<Usuario> getMembrosTime1() {
		return membrosEvento;
	}

	public void setMembrosTime1(List<Usuario> membrosTime1) {
		this.membrosEvento = membrosTime1;
	}



}
