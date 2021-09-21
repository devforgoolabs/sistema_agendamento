package com.goolab.models;

import java.io.Serializable;

public class Atributos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String grupo;
	
	public Atributos() {
	}
	
	public Atributos(String nome, String grupo) {
		this.nome = nome;
		this.grupo = grupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
}
