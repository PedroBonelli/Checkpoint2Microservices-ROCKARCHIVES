package br.com.fiap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_BANDAS")
public class BandaModel {
	
	private long id;
	private String nome;
	private String genero;
	private String anoFormacao;
	private List<AlbumModel> albums;
	private List<IntegranteModel> integrantes;
	
	public BandaModel() {
	}

	public BandaModel(long id, String nome, String genero, String anoFormacao, List<AlbumModel> albums,
			List<IntegranteModel> integrantes) {
		super();
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.anoFormacao = anoFormacao;
		this.albums = albums;
		this.integrantes = integrantes;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANDAS_SEQ")
	@SequenceGenerator(name = "BANDAS_SEQ", sequenceName="BANDAS_SEQ", allocationSize = 1)
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Column(name = "ANOFORMACAO")
	public String getAnoFormacao() {
		return anoFormacao;
	}

	
	public void setAnoFormacao(String anoFormacao) {
		this.anoFormacao = anoFormacao;
	}

	@OneToMany(mappedBy = "banda")
	public List<AlbumModel> getAlbums() {
		return albums;
	}

	public void setAlbums(List<AlbumModel> albums) {
		this.albums = albums;
	}

	@OneToMany(mappedBy = "banda")
	public List<IntegranteModel> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<IntegranteModel> integrantes) {
		this.integrantes = integrantes;
	}
	
	
	
	
	
}
