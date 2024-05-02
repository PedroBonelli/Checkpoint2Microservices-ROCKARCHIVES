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
@Table(name = "TB_INTEGRANTES")
public class IntegranteModel {
	
	private long id;
	private String nome;
	private String role;
	private String genero; 
	private BandaModel banda;
	
	
	public IntegranteModel() {
		
	}


	public IntegranteModel(long id, String nome, String role, String genero, BandaModel banda) {
		super();
		this.id = id;
		this.nome = nome;
		this.role = role;
		this.genero = genero;
		this.banda = banda;
	}



	@Id
	@Column(name = "ID") 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INTEGRANTES_SEQ")
	@SequenceGenerator(name = "INTEGRANTES_SEQ", sequenceName = "INTEGRANTES_SEQ", allocationSize = 1)
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
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
