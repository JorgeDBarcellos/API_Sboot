package com.compasso.api.resources;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cliente {


	@GeneratedValue
	@Id
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column (name = "datanascimento")
	private Integer datanascimento;
	
	
	public Cliente(String nome, String sexo, Integer datanascimento) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.datanascimento = datanascimento;
	}
	
	public Cliente() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Integer datanascimento) {
		this.datanascimento = datanascimento;
	}

	
	
}
