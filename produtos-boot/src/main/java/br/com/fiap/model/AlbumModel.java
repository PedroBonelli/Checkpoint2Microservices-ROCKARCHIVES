package br.com.fiap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ALBUMS")
public class AlbumModel {
	
	private long id;
	private String nome;
	private String anoLancamento;
	
	private String genero;
	private String gravadora;
	private BandaModel banda;
	
	public AlbumModel() {
		
	}

	public AlbumModel(long id, String nome, String anoLancamento, String genero, String gravadora, BandaModel banda) {
		super();
		this.id = id;
		this.nome = nome;
		this.anoLancamento = anoLancamento;
		this.genero = genero;
		this.gravadora = gravadora;
		this.banda = banda;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALBUMS_SEQ")
	@SequenceGenerator(name = "ALBUMS_SEQ", sequenceName="ALBUMS_SEQ", allocationSize = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "ANOLANCAMENTO")
	public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getGravadora() {
		return gravadora;
	}

	public void setGravadora(String gravadora) {
		this.gravadora = gravadora;
	}

	@ManyToOne
	@JoinColumn(name = "ID_BANDA", nullable = false)
	public BandaModel getBanda() {
		return banda;
	}

	public void setBanda(BandaModel banda) {
		this.banda = banda;
	}
	
	
	
	
	
}
